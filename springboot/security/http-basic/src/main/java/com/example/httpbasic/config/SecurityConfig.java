package com.example.httpbasic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

// * Spring Security의 동작 메커니즘
// Spring Security의 모든 것은 필터(Filter) 위에서 돌아간다.
// 웹 요청이 들어오면 실제 컨트롤러에 도달하기 전에 여러 개의 보안 필터를 순서대로 통과한다.
// 이 진입점이 DelegatingFilterProxy이다.
// 서블릿 컨테이너(톰캣)에 등록된 이 필터가 요청을 받아서 스프링이 관리하는 FilterChainProxy에게 위임하고,
// 이 FilterChainProxy가 내부적으로 여러 SecurityFilterChain을 들고 있다.
// 즉 "톰캣 필터 -> 스프링 빈으로 관리되는 보안 필터들"로 다리를 놓아주는 구조이다.
// - 요청 -> DelegatingFilterProxy -> FilterChainProxy -> [보안 필터 체인] -> DispatcherServlet -> Controller

// * DelegatingFilterProxy : "서블릿 컨테이너(톰캣)의 세계와 스프링의 세계를 이어주는 다리 역할을 하는 필터"이다.
// - Delegating(위임) +  Filter + Proxy(대리인) : 실제 일은 다른 녀석에게 위임하는 껍데기 필터
// 왜 이런 게 필요한가
// 핵심은 톰캣과 스프링이 서로 다른 세계라는 점이다.

// 서블릿 컨테이너는 Filter를 자기 규칙대로 등록하고 생성/관리한다.
//하지만 톰캣은 스프링 Bean을 전혀 모른다. 스프링 컨테이너 안에 뭐가 있는지 전혀 모른다.
// 그런데 우리가 쓰고 싶은 실제 보안 필터들(FilterChainProxy와 그 안의 인증/인가 필터)은 스프링 Bean이다.
// DI, 라이프사이클 관리 등 스프링 기능을 다 써야 하기때문이다.

// 필터는 톰캣에 등록돼야 하는데, 정작 실행하고 싶은 로직은 스프링 Bean이다. - 톰캣에 직접 스프링 Bean을 필터로 꽂을 수는 없다.
// 그래서 DelegatingFilterProxy 클래스갈 중간에서 다리를 놓는다.

// DelegatingFilterProxy 자신은 평범한 서블릿 필터라서 톰캣에 정상적으로 등록될 수 있다.(톰캣 입장에선 그냥 일반 필터중 하나)
// 하지만 실제로 요청이 들어오면, 스스로 처리하지 않고 스프링 컨테이너(ApplicationContext)에서 특정 이름의 Bean을 찾아 그 Bean에게 일을 넘긴다.
// Spring Security의 경우, 찾는 Bean 이름은 springSecurityFilterChain이고, 이 Bean의 정차게 바로 FilterChainProxy이다.

// * HTTP BASIC이란?
// HTTP 표준(RFC 7617)에 정의된 가장 기본적인 인증 방식이다.
// 별도의 로그인 페이지나 폼이 없이, HTTP 요청 헤더에 아이디/비밀번호를 실어 보내는 방식.

// * HTTP BASIC 인증 (Spring Security)
// - 프로토콜 관점의 동작 원리
// 1. 클라이언트가 보호된 자원에 그냥 접근하면, 서버가 401 Unauthorized와 함께 헤더를 응답한다. -> WWW-Authenticate: Basic realm="..."
// 2. 브라우저는 이걸 받으면 아이디/비밀번호를 입력하는 작은 팝업 창을 띄운다.
// 3. 사용자가 입력하면, 클라이언트는 username:password를 Base64로 인코딩해서 헤더에 담아 다시 요청한다. -> Authorization: Basic ZHVzZXI6cGFzc3dvcmQ=
// 4. 서버는 이 헤더를 디코딩해서 인증을 검증한다.

// - Spring Security에서의 동작원리 - BasicAuthenticationFilter가 담당
// 위 프로토콜을 필터 체인 안에서 실제로 처리하는 흐름
// 1. 요청이 들어오면


@Configuration
@EnableWebSecurity
public class SecurityConfig {
}
