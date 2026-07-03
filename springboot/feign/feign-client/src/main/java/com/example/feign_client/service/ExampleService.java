package com.example.feign_client.service;

import com.example.feign_client.client.ExampleClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// @Component와 기능은 똑같다.
@Service
// final이 붙은 필드의 생성자를 만들어줌
@RequiredArgsConstructor
public class ExampleService {
    private final ExampleClient exampleClient;  // final을 붙인 이유 : 생성자 주입을 강제하기 위해서

    public String getDataById(Long id) {
        return exampleClient.getData(id);
    }
}
