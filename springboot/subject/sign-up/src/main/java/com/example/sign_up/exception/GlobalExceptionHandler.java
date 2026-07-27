package com.example.sign_up.exception;

import com.example.sign_up.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateUserIdException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicateUserId(DuplicateUserIdException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponseDto(HttpStatus.CONFLICT.value(), e.getMessage()));

    }

    @ExceptionHandler(BoardNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleBoardNotFound(BoardNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> exception(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버 오류가 발생했습니다.")
                );
    }
}
