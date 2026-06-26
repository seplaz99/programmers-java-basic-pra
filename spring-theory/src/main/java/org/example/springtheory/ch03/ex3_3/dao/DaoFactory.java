package org.example.springtheory.ch03.ex3_3.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 어노테이션, @Configuration을 쓰는 순간 스프링 컨테이너가 관리
// DaoFactory를 스프링 빈 팩토리가 사용할 수 있는 설정정보로 리팩토링
// 애플리케이션 컨텍스트 또는 빈팩토리가 사용할 설정 정보라는 표시
@Configuration
public class DaoFactory {

    @Bean
    public SimpleConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
}
