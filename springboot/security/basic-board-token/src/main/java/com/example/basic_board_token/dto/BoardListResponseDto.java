package com.example.basic_board_token.dto;

import com.example.basic_board_token.domain.entity.Board;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BoardListResponseDto {

    private List<Board> boards;
    private boolean last;
    private int totalPages;
}
