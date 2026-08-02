package com.example.basic_board_token.controller;

import com.example.basic_board_token.dto.ErrorResponseDto;
import com.example.basic_board_token.dto.RefreshTokenResponseDto;
import com.example.basic_board_token.service.TokenService;
import com.example.basic_board_token.util.CookieUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "토큰 API", description = "Access Token 재발급, 로그아웃")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tokens")
public class TokenApiController {

    private final TokenService tokenService;

    @Operation(summary = "Access Token 재발급",
            description = "쿠키의 Refresh Token이 유효하면 Access/Refresh Token을 새로 발급한다. Refresh Token은 다시 쿠키로 내려가고, Access Token만 응답 바디로 돌아온다.")
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        RefreshTokenResponseDto refreshTokenResponseDto = tokenService.refresh(request.getCookies(), response);

        if ( refreshTokenResponseDto.isValidated() ) {
            return ResponseEntity.ok(refreshTokenResponseDto);
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponseDto(HttpStatus.UNAUTHORIZED.value(), "리프레시 토큰이 만료되었습니다."));
    }

    @Operation(summary = "로그아웃", description = "Refresh Token 쿠키를 삭제한다. 프론트는 이 호출 후 로컬에 들고 있던 Access Token도 함께 지워야 한다.")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        CookieUtil.deleteCookie(request, response, CookieUtil.REFRESH_TOKEN_COOKIE);

        return ResponseEntity.ok().build();
    }
}
