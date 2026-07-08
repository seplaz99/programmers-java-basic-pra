package com.example.sign_up.controller;

import com.example.sign_up.domain.entity.Board;
import com.example.sign_up.dto.BoardDetailResponseDto;
import com.example.sign_up.dto.BoardListResponseDto;
import com.example.sign_up.service.BoardService;
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
        List<Board> boards = boardService.getBoardList(page, size);
        int totalBoards = boardService.getTotalBoards();
        int totalpages = (int) Math.ceil((double) totalBoards / size);
        boolean last = page >= totalpages;

        return BoardListResponseDto.builder()
                .boards(boards)
                .last(last)
                .totalPages(totalpages)
                .build();
    }

    @GetMapping("/{id}")
    public BoardDetailResponseDto getBoardDetail(@PathVariable long id) {
        Board board = boardService.getBoardDetail(id);
        return BoardDetailResponseDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .created(board.getCreated())
                .userId(board.getUserId())
                .filePath(board.getFilePath())
                .build();
    }
}
