package com.example.basic_board.service;

import com.example.basic_board.domain.entity.Member;
import com.example.basic_board.domain.repository.MemberRepository;
import com.example.basic_board.dto.LoginRequestDto;
import com.example.basic_board.dto.MemberJoinRequestDto;
import com.example.basic_board.exception.DuplicateUserIdException;
import com.example.basic_board.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
// 이 클래스의 "모든 메서드"에 기본 적용된다.
// - readOnly = true의 효과
// "이 트랜잭션은 데이터를 안 바꾼다."라고 JPA한테 알려준다. 이 트랜잭션에서 조회만 하겠다는 힌트 -> 최적화
// 하이버네이트가 변경감지를 위한 스냅샷을 안 만들어 메모리/성능에 유리
// Insert/Update/Delete가 필요한 메서드는 @Transactional을 다시 붙인다.
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Transactional
    public void join(MemberJoinRequestDto dto) {
    // 아이디 중복 체크
    if (memberRepository.existsByUserId(dto.getUserId())) {
            // 에외 공통화
            throw new DuplicateUserIdException("[회원가입] 이미 존재하는 아이디입니다.");
        }

        memberRepository.save(memberMapper.toEntity(dto));
    }

    // Optional<Member> : NPE(NullPointerException) 예방
    // - 예전에는 "값이 없음"을 null로 표현했는데, null을 깜빡하고 그냥 쓰면 실행 중에 NPE가 터졌다.
    // 예) Member m = findByUserId("test"); m.getUserName(); // m이 null이면 터진다.
    // - 게다가 반환 타입만 봐서는 "null이 올 수 있는지" 알 수 가 없어 실숙하기가 쉬웠다.

    // Optional = "값이 없을 수도 있다."을 타입으로 알려주는 상자(Wrapper)
    // - 반환 타입이 Optional이면 "값이 없을 수 있으니 처리해라"라고 컴파일 단계에서 강제된다.
    // - 즉 '없을 수 있음'을 문서가 아니라 "타입"으로 표현해 실수를 막는 장치이다.

    // 상자(Wrapper)를 여는(값을 꺼내는) 주요 메서드
    // - isPresent()/isEmpty() : 값이 있는지/없는지 boolean으로 확인
    // - get() : 값을 꺼냄(비어있으면 예외! 되도록 쓰지 않는다.)
    // - orElse(기본값) : 있으면 그 값, 없으면 기본값(기본값은 항상 미리 계산됨)
    // - orElseGet(함수) : 있으면 그 값, 없으면 함수를 실행(없을때만 계산)
    // - map(함수) : 값이 있으면 다른 값으로 변환, 없으면 그대로 empty
    // - filter(조건) : 값이 있고 조건을 만족하면 유지, 아니면 empty 

    public Optional<Member> login(LoginRequestDto dto) {
        return memberRepository.findByUserId(dto.getUsername())
                .filter(
                       member -> member.getPassword().equals(dto.getPassword())
                );
    }
}