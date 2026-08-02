package com.example.basic_board_token.controller;

import com.example.basic_board_token.dto.*;
import com.example.basic_board_token.service.MemberService;
import com.example.basic_board_token.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원 API", description = "회원가입, 로그인, 로그아웃 (JWT 기반)")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberApiController {

    private final MemberService memberService;
    private final TokenService tokenService;

    @Operation(summary = "회원가입", description = "아이디/비밀번호/이름으로 새 회원을 등록한다. 성공 시 로그인 페이지 경로를 돌려준다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "가입 성공"),
            @ApiResponse(responseCode = "409", description = "이미 존재하는 아이디",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping("/join")
    public MemberJoinResponseDto join(@RequestBody MemberJoinRequestDto dto) {
        memberService.join( dto );

        return new MemberJoinResponseDto("/members/login");
    }

    @Operation(summary = "로그인",
            description = "아이디/비밀번호로 로그인한다. 성공 시 Access Token은 응답 바디로, Refresh Token은 HttpOnly 쿠키로 내려주고 loggedIn=true 를, " +
                    "실패 시 loggedIn=false 와 안내 메시지를 돌려준다.")
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto dto, HttpServletResponse response) {
        return memberService.login( dto )
                .map(
                        member -> {
                            TokenService.TokenPair tokenPair = tokenService.issueTokenWithRefreshCookie(member, response);

                            return LoginResponseDto.success(tokenPair.accessToken());
                        }
                )
                .orElseGet(LoginResponseDto::fail);
    }
}
