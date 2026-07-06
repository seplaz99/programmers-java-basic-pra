package com.example.sign_up.service;

import com.example.sign_up.domain.entity.Member;
import com.example.sign_up.domain.repository.MemberRepository;
import com.example.sign_up.dto.MemberJoinRequestDto;
import com.example.sign_up.exception.DuplicateUserIdException;
import com.example.sign_up.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Transactional
    public void join(MemberJoinRequestDto dto) {
        if (memberRepository.existsByUserId(dto.getUserId())) {
            throw new DuplicateUserIdException("이미 존재하는 아이디입니다.");
        }

        Member member = memberMapper.toEntity(dto);
        memberRepository.save(member);
    }
}
