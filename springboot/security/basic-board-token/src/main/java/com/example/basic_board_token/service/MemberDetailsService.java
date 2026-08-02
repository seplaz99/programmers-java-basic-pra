package com.example.basic_board_token.service;

import com.example.basic_board_token.config.security.CustomUserDetails;
import com.example.basic_board_token.domain.entity.Member;
import com.example.basic_board_token.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 아이디입니다. userId=" + userId));

        return CustomUserDetails.builder()
                .member(member)
                .build();
    }
}
