package com.example.basic_board_token.mapper;

import com.example.basic_board_token.domain.entity.Member;
import com.example.basic_board_token.dto.MemberJoinRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberMapper {

    private final PasswordEncoder passwordEncoder;

    public Member toEntity(MemberJoinRequestDto dto) {
        return Member.builder()
                .userId(dto.getUserId())
                .password(passwordEncoder.encode(dto.getPassword()))
                .userName(dto.getUserName())
                .build();
    }
}
