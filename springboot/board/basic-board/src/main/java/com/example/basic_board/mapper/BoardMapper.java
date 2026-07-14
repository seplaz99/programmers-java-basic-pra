package com.example.basic_board.mapper;

import com.example.basic_board.domain.entity.Board;
import com.example.basic_board.domain.entity.Comment;
import com.example.basic_board.dto.BoardWithCommentsResponseDto;
import com.example.basic_board.dto.CommentResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BoardMapper {
    public BoardWithCommentsResponseDto toBoardWithCommentsResponseDto(Board board) {
        List<CommentResponseDto> comments = board.getComments().stream()
                .map(this::toCommentDto)
                .toList();

        return BoardWithCommentsResponseDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .userId(board.getUserId())
                .created(board.getCreated())
                .filePath(board.getFilePath())
                .comments(comments)
                .build();
    }

    public CommentResponseDto toCommentDto(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .userId(comment.getUserId())
                .content(comment.getContent())
                .created(comment.getCreated())
                .build();
    }
}
