package com.example.token.controller;

import com.example.token.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserApiController {

    @PostMapping("/join")
    public void join(@RequestBody SignUpRequestDto requestDto) {

    }
}
