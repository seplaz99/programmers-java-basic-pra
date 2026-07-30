package com.example.token.controller;

import com.example.token.config.jwt.JwtProperties;
import com.example.token.config.security.CustomUserDetails;
import com.example.token.domain.entity.User;
import com.example.token.dto.*;
import com.example.token.service.UserService;
import com.example.token.util.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserApiController {

    private final UserService userService;
    private final JwtProperties jwtProperties;

    @PostMapping("/join")
    public SignUpResponseDto join(@RequestBody SignUpRequestDto requestDto) {
        userService.signUp(requestDto);

        return SignUpResponseDto.builder()
                .url("/user/login")
                .build();

    }

    @PostMapping("/login")
    public SignInResponseDto login(
            @RequestBody SignInRequestDto requestDto,
            HttpServletResponse response
    ) {
        SignInResponseDto signInResponseDto = userService.login(requestDto);

        CookieUtil.addCookie(
                response,
                CookieUtil.REFRESH_TOKEN_COOKIE,
                signInResponseDto.getRefreshToken(),
                (int) jwtProperties.getRefreshTokenValidity().toSeconds()
        );

        signInResponseDto.setAccessToken(null);

        return null;
    }

    @GetMapping("/info")
    public UserInfoResponseDto getUserInfo(@AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();

        return UserInfoResponseDto.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .username(user.getName())
                .role(user.getRole())
                .build();
    }
}
