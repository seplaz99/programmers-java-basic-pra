package com.example.basic_board_token.controller;

import com.example.basic_board_token.config.security.CustomUserDetails;
import com.example.basic_board_token.dto.CommentWriteRequestDto;
import com.example.basic_board_token.service.CommentService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "댓글 API", description = "게시글에 댓글 관련")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards/{boardId}/comments")
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping
    public void addComment(
            @AuthenticationPrincipal CustomUserDetails principal,
            @Parameter(description = "댓글을 달 게시글 id", example = "1")
            @PathVariable long boardId,
            @RequestBody CommentWriteRequestDto dto
    ) {
        commentService.addComment(boardId, principal.getUsername(), dto.getContent());
    }
}
