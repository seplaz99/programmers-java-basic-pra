package com.example.basic_board.controller;

import com.example.basic_board.domain.entity.Board;
import com.example.basic_board.dto.BoardListResponseDto;
import com.example.basic_board.dto.BoardWriteRequestDto;
import com.example.basic_board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

}
