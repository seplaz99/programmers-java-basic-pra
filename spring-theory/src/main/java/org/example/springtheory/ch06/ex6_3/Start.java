package org.example.springtheory.ch06.ex6_3;

import org.example.springtheory.ch06.ex6_3.dao.DaoFactory;
import org.example.springtheory.ch06.ex6_3.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// 스프링 AOP
// 문제점
// ex6_2는 부가기능을 적용할 빈마다 ProxyFactoryBean을 '하나씩' 설정해야 한다.
// (target 지점 + advisor 등럭을 빈 개수만큼 반복)

// '자동 프록시 생성기'가 프록시 적용을 자동화 한다.


public class Start {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserService userService = context.getBean("userService", UserService.class);
        userService.upgradeLevels();    // Pointcut("upgrade") -> TransactionAdvice 적용
        // userService.add(...); // 매칭 안됨 -> 트랜잭션 없이 그대로 진행


    }
}
