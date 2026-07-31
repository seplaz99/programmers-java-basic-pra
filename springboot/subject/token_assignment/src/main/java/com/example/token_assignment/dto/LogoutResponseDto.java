package com.example.token_assignment.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LogoutResponseDto {

    String message;
    String url;
}
