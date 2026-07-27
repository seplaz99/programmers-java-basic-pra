package org.example.springtheory.ch05.ex5_1.dao;

import org.example.springtheory.ch05.ex5_1.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
    @Bean
    public UserService userService() {
        return new UserService(userDAO());
    }

    @Bean
    public UserDAO userDAO() {
        return new UserDAO(jdbcContext());
    }

    @Bean
    public JdbcContext jdbcContext() {
        return new JdbcContext(connectionMaker());
    }

    @Bean
    public SimpleConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }

}
