package com.example.basic_board.service;

import com.example.basic_board.domain.entity.Member;
import com.example.basic_board.domain.repository.MemberRepository;
import com.example.basic_board.dto.LoginRequestDto;
import com.example.basic_board.mapper.MemberMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

// 순수 단위 테스트 - 서비스 로직만 검증한다.

// Mockito란?
// - "가짜 객체(Mock)"를 쉽게 만들어 주는 자바 테스트 라이브러리이다.
// - Mock = 진짜와 유사한 모양의 빈 껍데기 -> 시나리오을 심어줄 수 있다.

// 왜 가짜가 필요하나?
// - 단위 테스트 "대상 하나(MemberService)"가 제대로 동작하는지만 보고 싶음.
// - 그런데 MemberService는 MemberRepository에 의존한다.
// -> 진자 리포지토리를 쓰면 1) DB가 떠 있어야 하고 2) DB/리포지토리 버그까지 섞여
// "무엇을 틀렸는지" 불분명하다.
// - 그래서 리포지토리를 '가짜'로 바꿔, 그 행동을 내가 정해 놓고 -> 순수하게 서비스 로직만 검증한다.

// 자주 쓰는 Mockito 문법
// @ExtendWith(MockitoExtension.class) : 이 테스트에서 Mockito 기능을 건다.
// @Mock : 가짜 객체를 만든다.
// @InjectMocks : 테스트 대상을 만들고 위 @Mock 등을 주입한다.

// 스터빙 : "이렇게 부르면 이 값을 돌려줘라"
// - given(repo.existByUserId("newbie)).willReturn(false);  // 특정인자 -> false 반환하도록
// - given(repo.findByUserId("test")).willReturn(Optional.of(member)); // 회원을 담아 반환
// - given(repo.count()).willThrow(new RuntimeException()); // 호출되면 예외를 던지게
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository; // 가짜 레포지토리

    @Mock
    private MemberMapper memberMapper; // 가짜 매퍼

    @InjectMocks
    private MemberService memberService; // 테스트 대상 (위 두 Mock이 주입됨)

    @Test
    @DisplayName("로그인 - 아이디가 있고 비밀번호가 일치하면 회원을 담은 Optional을 반환한다.")
    void login_아이디와_비밀번호가_맞으면_회원을_반환한다() {
        // given - "test/1234" 회원이 DB에 있다고 가정
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
        // given - "test/1234" 회원이 DB에 있다고 가정
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
}