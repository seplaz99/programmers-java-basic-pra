package com.example.basic_board_token.domain.repository;

import com.example.basic_board_token.domain.entity.Board;
import com.example.basic_board_token.dto.BoardAuthorStatsResponseDto;
import com.example.basic_board_token.dto.BoardListItemResponseDto;
import com.example.basic_board_token.dto.BoardSearchRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BoardRepositoryCustom {

    Page<BoardListItemResponseDto> searchBoards(BoardSearchRequestDto condition, Pageable pageable);

    Optional<Board> findWithComments(Long id);

    List<BoardAuthorStatsResponseDto> countBoardsByAuthor(long minCout);
}
