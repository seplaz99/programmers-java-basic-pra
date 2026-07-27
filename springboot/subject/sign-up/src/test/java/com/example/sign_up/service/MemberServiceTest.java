package com.example.sign_up.service;

import com.example.sign_up.domain.entity.Member;
import com.example.sign_up.domain.repository.MemberRepository;
import com.example.sign_up.dto.LoginRequestDto;
import com.example.sign_up.dto.MemberJoinRequestDto;
import com.example.sign_up.exception.DuplicateUserIdException;
import com.example.sign_up.mapper.MemberMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;

    @Mock
    private MemberMapper memberMapper;

    @InjectMocks
    private MemberService memberService;

    @Test
    @DisplayName("로그인 - 아이디가 있고 비밀번호가 일치하면 회원을 담은 Optional을 반환한다.")
    void login_아이디와_비밀번호가_맞으면_회원을_반환한다() {
        // given
        Member member = Member.builder()
                .userId("test")
                .password("1234")
                .userName("홍길동")
                .build();
        given( memberRepository.findByUserId("test") ).willReturn( Optional.of(member) );

        LoginRequestDto requestDto = new LoginRequestDto();
        requestDto.setUsername("test");
        requestDto.setPassword("1234");

        // when
        Optional<Member> result = memberService.login(requestDto);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getUserName()).isEqualTo("홍길동");
    }

    @Test
    @DisplayName("로그인 - 비밀번호가 틀리면 빈 Optional을 반환한다.")
    void login_비밀번호가_틀리면_빈_Optional() {
        // given
        Member member = Member.builder()
                .userId("test")
                .password("1234")
                .userName("홍길동")
                .build();
        given( memberRepository.findByUserId("test") ).willReturn( Optional.of(member) );

        LoginRequestDto requestDto = new LoginRequestDto();
        requestDto.setUsername("test");
        requestDto.setPassword("9999");

        // when
        Optional<Member> result = memberService.login(requestDto);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("로그인 - 아이디가 없으면 빈 Optional을 반환한다.")
    void login_아이디가_없으면_빈_Optional() {
        // given
        given( memberRepository.findByUserId("nobody") ).willReturn( Optional.empty() );

        LoginRequestDto requestDto = new LoginRequestDto();
        requestDto.setUsername("nobody");
        requestDto.setPassword("9999");

        // when
        Optional<Member> result = memberService.login(requestDto);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("회원가입 - 아이디가 중복이 아니면 회원을 지정한다.")
    void join_중복이_아니면_저장한다() {
        // given
        MemberJoinRequestDto requestDto = new MemberJoinRequestDto();
        requestDto.setUserId("test");
        requestDto.setPassword("1234");
        requestDto.setUserName("홍길동");

        Member member = Member.builder()
                .userId("test")
                .password("1234")
                .userName("홍길동")
                .build();
        given( memberRepository.existsByUserId("test") ).willReturn(false);
        given( memberMapper.toEntity(requestDto) ).willReturn( member );

        // when
        memberService.join(requestDto);

        // then
        verify(memberRepository).save(member);
    }

    @Test
    @DisplayName("회원가입 - 아이디가 중복이면 DuplicateUserIdException을 던지고 저장하지 않는다.")
    void join_중복이면_예외() {
        // given
        MemberJoinRequestDto requestDto = new MemberJoinRequestDto();
        requestDto.setUserId("test");
        requestDto.setPassword("1234");
        requestDto.setUserName("홍길동");

        given( memberRepository.existsByUserId("test") ).willReturn(true);

        // when & then
        assertThatThrownBy(() ->  memberService.join(requestDto))
                .isInstanceOf(DuplicateUserIdException.class)
                .hasMessageContaining("[회원가입] 이미 존재하는 아이디입니다.");
        verify(memberRepository, never()).save(any());
    }
}