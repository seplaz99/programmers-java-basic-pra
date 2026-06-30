package org.example.springtheory.ch05.ex5_2.dao;

import org.example.springtheory.ch05.ex5_2.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

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
        return new JdbcContext(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/springtheory");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        return dataSource;
    }

}
