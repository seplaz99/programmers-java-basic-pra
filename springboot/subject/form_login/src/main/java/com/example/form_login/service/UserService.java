package com.example.form_login.service;

import com.example.form_login.domain.entity.User;
import com.example.form_login.domain.repository.UserRepository;
import com.example.form_login.dto.SignUpRequestDto;
import com.example.form_login.exception.DuplicateUserIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(SignUpRequestDto signUpRequestDto) {
        if (userRepository.existsByUserId(signUpRequestDto.getUserId())) {
            throw new DuplicateUserIdException("[회원가입] 이미 사용중인 아이디입니다.");
        }

        User user = signUpRequestDto.toUser(passwordEncoder.encode(signUpRequestDto.getPassword()));

        userRepository.save(user);
    }
}
