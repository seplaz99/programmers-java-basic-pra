package com.example.sign_up.controller;

import com.example.sign_up.constant.SessionConst;
import com.example.sign_up.dto.LoginRequestDto;
import com.example.sign_up.dto.LoginResponseDto;
import com.example.sign_up.dto.MemberJoinRequestDto;
import com.example.sign_up.dto.MemberJoinResponseDto;
import com.example.sign_up.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/join")
    public MemberJoinResponseDto join(@RequestBody MemberJoinRequestDto dto){
        memberService.join(dto);

        return new MemberJoinResponseDto("/members/login");
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto request, HttpSession session){
        return memberService.login(request)
                .map(member -> {
                    session.setAttribute(SessionConst.USER_ID, member.getUserId());
                    session.setAttribute(SessionConst.USER_NAME, member.getUserName());
                    return LoginResponseDto.success();
                })
                .orElseGet(LoginResponseDto::fail);
    }
}
