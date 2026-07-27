package com.example.sign_up.mapper;

import com.example.sign_up.domain.entity.Member;
import com.example.sign_up.dto.MemberJoinRequestDto;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member toEntity(MemberJoinRequestDto dto) {
        return Member.builder()
                .userId(dto.getUserId())
                .password(dto.getPassword())
                .userName(dto.getUserName())
                .build();
    }
}
