package org.example.springtheory.ch06.ex6_4;

import org.example.springtheory.ch06.ex6_4.dao.DaoFactory;
import org.example.springtheory.ch06.ex6_4.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// 에너테이션 기반 선언적 트랜잭션

public class Start {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserService userService = context.getBean("userService", UserService.class);
        userService.upgradeLevels();
    }
}
