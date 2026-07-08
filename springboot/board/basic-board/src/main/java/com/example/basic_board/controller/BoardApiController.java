package com.example.basic_board.controller;

import com.example.basic_board.domain.entity.Board;
import com.example.basic_board.dto.BoardDetailResponseDto;
import com.example.basic_board.dto.BoardListResponseDto;
import com.example.basic_board.dto.BoardWriteRequestDto;
import com.example.basic_board.service.BoardService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping
    public BoardListResponseDto getBoardList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        // 게시글 목록
        List<Board> boards = boardService.getBoardList(page, size);

        // 전체 게시글 수 가져오기
        int totalBoards = boardService.getTotalBoards();

        // 전체 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalBoards / size);

        // 마지막 페이 여부
        boolean last = page >= totalPages;

        return BoardListResponseDto.builder()
                .boards(boards)
                .last(last)
                .totalPages(totalPages)
                .build();
    }

    @PostMapping
    public void saveBoard(@ModelAttribute BoardWriteRequestDto dto) {
        boardService.saveBoard(dto.getUserId(), dto.getTitle(), dto.getContent(), dto.getFile());
    }

    @GetMapping("/{id}")
    public BoardDetailResponseDto getBoard(@PathVariable long id) {
        Board boardDetail = boardService.getBoardDetail(id);
        return BoardDetailResponseDto.builder()
                .title(boardDetail.getTitle())
                .content(boardDetail.getContent())
                .userId(boardDetail.getUserId())
                .filePath(boardDetail.getFilePath())
                .created(boardDetail.getCreated())
                .build();
    }

    // ResponseEntity는 HTTP 응답의 3가지를 직접 제어하게 해주는 상자이다.
    // [상태코드] + [헤더] + [본문(body)]
    // 그냥 Resource만 리턴하면 파일 내용은 내려가지만,
    // Content-Disposition: attachment 헤더를 붙일 방법이 없다.
    // -> 그러면 다운로드가 아니라 브라우저가 파일을 그냥 열어버리고, 저장 파일명도 못 정한다.
    @GetMapping("/file/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename, HttpServletResponse response) throws IOException {
        return null;
    }
}
