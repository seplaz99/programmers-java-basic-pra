package com.example.basic_board_token.service;

import com.example.basic_board_token.config.security.CustomUserDetails;
import com.example.basic_board_token.domain.entity.Member;
import com.example.basic_board_token.domain.repository.MemberRepository;
import com.example.basic_board_token.dto.LoginRequestDto;
import com.example.basic_board_token.dto.MemberJoinRequestDto;
import com.example.basic_board_token.exception.DuplicateUserIdException;
import com.example.basic_board_token.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void join(MemberJoinRequestDto dto) {
        if (memberRepository.existsByUserId(dto.getUserId())) {
            log.warn("회원가입 실패(아이디 중복) : userId={}", dto.getUserId());

            throw new DuplicateUserIdException("[회원가입] 이미 존재하는 아이디입니다.");
        }

        memberRepository.save(memberMapper.toEntity(dto));
        log.info("회원가입 완료 : userId={}, userName={}", dto.getUserId(), dto.getUserName());
    }

    public Optional<Member> login(LoginRequestDto dto) {
        try {
            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
            );

            CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
            log.info("로그인 성공 : username = {}", dto.getUsername());

            return Optional.of(principal.getMember());
        } catch (AuthenticationException e) {
            log.warn("로그인 실패 : username = {}",  dto.getUsername());

            return Optional.empty();
        }
    }
}