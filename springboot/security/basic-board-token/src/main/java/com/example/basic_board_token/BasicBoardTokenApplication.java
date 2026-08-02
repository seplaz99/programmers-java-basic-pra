package com.example.basic_board_token;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BasicBoardTokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicBoardTokenApplication.class, args);
	}
}
