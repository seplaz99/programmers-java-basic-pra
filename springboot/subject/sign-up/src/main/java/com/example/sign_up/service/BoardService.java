package com.example.sign_up.service;

import com.example.sign_up.domain.entity.Board;
import com.example.sign_up.domain.repository.BoardRepository;
import com.example.sign_up.dto.BoardDeleteRequestDto;
import com.example.sign_up.dto.BoardUpdateRequestDto;
import com.example.sign_up.dto.BoardWriteRequestDto;
import com.example.sign_up.exception.BoardNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final FileService fileService;

    public List<Board> getBoardList(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
        return boardRepository.findAll(pageable).getContent();
    }

    public int getTotalBoards() {
        return (int) boardRepository.count();
    }

    @Transactional
    public void saveBoard(BoardWriteRequestDto dto) {
        String filePath = fileService.storeFile(dto.getFile());

        Board build = Board.builder()
                .userId(dto.getUserId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .filePath(filePath)
                .created(LocalDateTime.now())
                .build();

        boardRepository.save(build);
    }

    public Board getBoardDetail(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException("[BOARD] 게시글을 찾을 수 없습니다. id = " + id));
    }

    @Transactional
    public void updateBoard(long id, BoardUpdateRequestDto dto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(
                        () -> new BoardNotFoundException("[BOARD] 수정할 게시글을 찾을 수 없습니다. id = " + id)
                );

        String filePath = board.getFilePath();
        if (dto.isFileFlag()) {
            fileService.deleteFile(filePath);
            filePath = fileService.storeFile(dto.getFile());
        }

        board.update(dto.getTitle(), dto.getContent(), filePath);
    }

    @Transactional
    public void deleteBoard(long id, BoardDeleteRequestDto dto) {
        if ( !boardRepository.existsById(id) ) {
            throw new BoardNotFoundException("[BOARD] 삭제할 게시글을 찾을 수 없습니다. id = " + id);
        }

        boardRepository.deleteById(id);
        fileService.deleteFile(dto.getFilePath());
    }
}
