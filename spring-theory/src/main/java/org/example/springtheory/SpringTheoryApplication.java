package org.example.springtheory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 자기 자신보다 하위 문서만 가능
@SpringBootApplication
public class SpringTheoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTheoryApplication.class, args);
    }

}
