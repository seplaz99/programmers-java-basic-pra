package com.example.basic_board.controller;

import com.example.basic_board.dto.LoginRequestDto;
import com.example.basic_board.dto.LoginResponseDto;
import com.example.basic_board.dto.MemberJoinRequestDto;
import com.example.basic_board.dto.MemberJoinResponseDto;
import com.example.basic_board.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/join")
    public MemberJoinResponseDto join(@RequestBody MemberJoinRequestDto dto) {
        memberService.join(dto);
        return new MemberJoinResponseDto("/members/login");
    }

    @GetMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto dto, HttpSession session) {
        return memberService.login(dto)
                .map(
                        member -> {
                            session.setAttribute("userId", member.getUserId());
                            session.setAttribute("userName", member.getUserName());
                            return LoginResponseDto.success();
                        }
                ).orElseGet(LoginResponseDto::fail);
    }
}
