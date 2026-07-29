package com.example.token.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

// JWT(JSON Web Token)
// JWT는 당사자 간에 정보를 JSON 객체로 안전하게 전달하기 위한 토큰 표준.
// '.'(점)으로 구분된 세 부분으로 구성된다.
// xxxxx.yyyyy.zzzzz -> Header.Payload.Signature

// 1. Header
// - 토큰의 메타 정보 : 서명 알고리즘(alg), 토큰 타입(type)
// - 예 : {"alg" : "HS256", "type" : "JWT"}
// - 이 JSON을 Base64Url 인코딩한 것이 첫 번째 부분

// 2. Payload
// - 실제 전달할 데이터인 클레임(Claim)들을 담는다.
// - 주의 : 암호화가 아니라 '인코딩'일 뿐 -> 누구나 디코딩해서 볼 수 있으므로 비밀번호 등 민감정보를 절대 넣으면 안된다.

// 클레임이란
// - Payload에 담기는 정보 한 조각(key-value 쌍 하나하나)를 클레임이라 한다.
// - 토큰이 "이 사용자는 test이다", "이 토큰은 10시에 만료된다" 같은 사실을 '주장(claim)'한다는 의미
// 서명이 유효하면 그 주장들을 신뢰할 수 있다.

// 클레임의 3가지 종류
// 1) 등록된 클레임
// 2) 공개 클레임
// 3) 비공개 클레임

// 3. Signature

@Configuration
@EnableWebSecurity
public class SecurityConfig {
}
