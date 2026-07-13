package com.example.basic_board.domain.repository;

import com.example.basic_board.dto.BoardListItemResponseDto;
import com.example.basic_board.dto.BoardSearchRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {

    Page<BoardListItemResponseDto> searchBoards(BoardSearchRequestDto condition, Pageable pageable);

}
