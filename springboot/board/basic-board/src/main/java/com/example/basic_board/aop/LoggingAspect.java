package com.example.basic_board.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// @Aspect
// - " 이 클래스는 공통 기능(횡단 관심사)을 모아둔 Aspect이다"라고 선언하는 어노테이션이다.
// - 이 어노테이션을 붙어야 스프링 AOP가 이 클래스 안의 포인트컷/어드바이스를 인식한다.
// - "AOP 규칙을 담고 있다"는 표시일 뿐, 스프링이 관리하는 빈으로 등록해주지 않는다. -> @Component 필요
// - 스프링 컨테이너에 빈으로 등록해야, 스프링이 이 Aspect를 찾아서 실제로 적용한다.
@Aspect
@Component
public class LoggingAspect {
}
