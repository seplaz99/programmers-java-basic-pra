package com.example.basic_board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class MemberController {     // 화면 이동만 담당
    @GetMapping("/join")
    public String join() {
        return "sign-up";
    }
}
