package com.example.basic_board_token.scheduler;

import com.example.basic_board_token.domain.entity.Board;
import com.example.basic_board_token.domain.repository.BoardRepository;
import com.example.basic_board_token.domain.repository.CommentRepository;
import com.example.basic_board_token.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class BoardBatchScheduler {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Scheduled(cron = "0 0 9 * * *", zone = "Asia/Seoul")
    public void dailyReport() {
        log.info("[일일 리포트 배치 시작]");

        long members = memberRepository.count();
        long boards = boardRepository.count();
        long comments = commentRepository.count();

        log.info("[일일 리포트] 회원 {}명, 게시글 {}건, 댓글{}건", members, boards, comments);
    }

    @Scheduled(initialDelay = 10_000, fixedDelay = 3_600_000)
    public void reportOrphanFiles() {
        log.info("[고아 파일 점검 배치 시작]");

        File dir = new File(uploadDir).getAbsoluteFile();
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            log.info("[고아 파일 점검] 업로드 디렉토리가 비어 있음");
            return;
        }

        Set<String> referenced = boardRepository.findAll().stream()
                .map(Board::getFilePath)
                .filter(Objects::nonNull)
                .map(path -> new File(path).getName())
                .collect(Collectors.toSet());

        int orphanCount = 0;
        for (File file : files) {
            if (!referenced.contains(file.getName())) {
                orphanCount++;
                log.warn("[고아 파일 발견] 어느 게시글도 참조하지 않음 : {}", file.getName());
            }
        }

        log.info("[고아 파일 점검 완료] 전체 {}개 중 고아 {}개", files.length, orphanCount);
    }
}
