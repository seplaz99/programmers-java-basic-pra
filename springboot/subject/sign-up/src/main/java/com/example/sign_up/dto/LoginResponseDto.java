package com.example.sign_up.dto;

import lombok.*;

@Getter
@AllArgsConstructor
public class LoginResponseDto {
    private boolean loggedIn;
    private String url;
    private String message;

    public static LoginResponseDto success(){
        return new LoginResponseDto(true, "/", "로그인에 성공했습니다.");
    }

    public static LoginResponseDto fail(){
        return new LoginResponseDto(false, null, "로그인에 실패했습니다.");
    }
}
