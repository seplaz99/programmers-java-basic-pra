package com.example.basic_board_token.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.annotations.Generated;

/**
 * com.example.basic_board.dto.QBoardAuthorStatsResponseDto is a Querydsl Projection type for BoardAuthorStatsResponseDto
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QBoardAuthorStatsResponseDto extends ConstructorExpression<BoardAuthorStatsResponseDto> {

    private static final long serialVersionUID = -1506209417L;

    public QBoardAuthorStatsResponseDto(com.querydsl.core.types.Expression<String> userId, com.querydsl.core.types.Expression<String> userName, com.querydsl.core.types.Expression<Long> boardCount) {
        super(BoardAuthorStatsResponseDto.class, new Class<?>[]{String.class, String.class, long.class}, userId, userName, boardCount);
    }

}

