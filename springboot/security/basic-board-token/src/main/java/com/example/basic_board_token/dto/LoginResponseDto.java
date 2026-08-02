package com.example.basic_board_token.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {

    private boolean successed;
    private String url;
    private String message;
    private String accessToken;

    // 로그인 성공
    public static LoginResponseDto success(String accessToken) {
        return new LoginResponseDto(true, "/", "로그인에 성공했습니다.", accessToken);
    }

    // 로그인 실패
    public static LoginResponseDto fail() {
        return new LoginResponseDto(false, null, "아이디 또는 비밀번호가 일치하지 않습니다.", null);
    }
}
