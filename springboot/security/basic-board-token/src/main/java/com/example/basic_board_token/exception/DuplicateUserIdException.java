package com.example.basic_board_token.exception;

public class DuplicateUserIdException extends RuntimeException {

    public DuplicateUserIdException(String message) {
        super(message);
    }
}
