package com.example.token.service;

import com.example.token.domain.repository.UserRepository;
import com.example.token.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public void signUp(SignUpRequestDto requestDto) {

    }
}
