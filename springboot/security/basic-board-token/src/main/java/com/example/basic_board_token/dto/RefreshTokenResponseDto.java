package com.example.basic_board_token.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RefreshTokenResponseDto {

    private boolean validated;
    private String accessToken;
}
