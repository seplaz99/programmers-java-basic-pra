package com.example.token_assignment.service;

import com.example.token_assignment.config.security.CustomUserDetails;
import com.example.token_assignment.domain.entity.User;
import com.example.token_assignment.domain.repository.UserRepository;
import com.example.token_assignment.dto.SignInRequestDto;
import com.example.token_assignment.dto.SignInResponseDto;
import com.example.token_assignment.dto.SignUpRequestDto;
import com.example.token_assignment.exception.DuplicateUserIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Transactional
    public void signUp(SignUpRequestDto requestDto) {
        if (userRepository.existsByUserId(requestDto.getUserId())) {
            throw new DuplicateUserIdException("[회원가입] 이미 사용중인 아이디입니다.");
        }

        User user = requestDto.toUser(passwordEncoder.encode(requestDto.getPassword()));

        userRepository.save(user);
    }

    public SignInResponseDto login(SignInRequestDto requestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.getUserId(), requestDto.getPassword())
        );

        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        TokenService.TokenPair tokenPair = tokenService.issueToken(user);

        return SignInResponseDto.builder()
                .isLoggedIn(true)
                .url("/")
                .userName(user.getName())
                .userId(user.getUserId())
                .accessToken(tokenPair.accessToken())
                .refreshToken(tokenPair.refreshToken())
                .message("로그인 성공")
                .build();
    }
}
