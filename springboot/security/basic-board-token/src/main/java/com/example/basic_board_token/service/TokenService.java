package com.example.basic_board_token.service;

import com.example.basic_board_token.config.jwt.JwtProperties;
import com.example.basic_board_token.config.jwt.TokenProvider;
import com.example.basic_board_token.config.jwt.TokenStatus;
import com.example.basic_board_token.domain.entity.Member;
import com.example.basic_board_token.dto.RefreshTokenResponseDto;
import com.example.basic_board_token.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenProvider tokenProvider;
    private final JwtProperties jwtProperties;

    public record TokenPair(String accessToken, String refreshToken) {}

    public TokenPair issueToken(Member member) {
        String accessToken = tokenProvider.generateToken(member, jwtProperties.getAccessTokenValidity());
        String refreshToken = tokenProvider.generateToken(member, jwtProperties.getRefreshTokenValidity());

        return new TokenPair(accessToken, refreshToken);
    }

    public TokenPair issueTokenWithRefreshCookie(Member member, HttpServletResponse response) {
        TokenPair tokenPair = issueToken(member);

        CookieUtil.addCookie(
                response,
                CookieUtil.REFRESH_TOKEN_COOKIE,
                tokenPair.refreshToken(),
                (int) jwtProperties.getRefreshTokenValidity().toSeconds()
        );

        return tokenPair;
    }

    public RefreshTokenResponseDto refresh(Cookie[] cookies, HttpServletResponse response) {
        String refreshToken = getRefreshToken(cookies);

        if (refreshToken != null && tokenProvider.validateToken(refreshToken) == TokenStatus.VALID) {

            Member member = tokenProvider.getTokenDetails(refreshToken);
            TokenPair tokenPair = issueTokenWithRefreshCookie(member, response);

            return RefreshTokenResponseDto.builder()
                    .validated(true)
                    .accessToken(tokenPair.accessToken())
                    .build();
        }

        return RefreshTokenResponseDto.builder()
                .validated(false)
                .build();
    }

    private String getRefreshToken(Cookie[] cookies) {

        if (cookies == null) return null;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(CookieUtil.REFRESH_TOKEN_COOKIE)) {
                return cookie.getValue();
            }
        }

        return null;
    }
}