package com.example.feign_client.service;

import com.example.feign_client.client.ExampleClient;
import com.example.feign_client.dto.DataRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// @Component와 기능은 똑같다.
@Service
@RequiredArgsConstructor
public class ExampleService {

    private final ExampleClient exampleClient;

    public String getDataById(Long id) {
        return exampleClient.getData(id);
    }

    public String createData(String name, int value) {
        return exampleClient.createData(
                DataRequest.builder()
                        .name(name)
                        .value(value)
                        .build()
        );
    }

    public String updateData(Long id, String name, int value) {
        return exampleClient.updateData(
                id,
                DataRequest.builder()
                        .name(name)
                        .value(value)
                        .build()
        );
    }

    public String deleteData(Long id) {
        return exampleClient.deleteData(id);
    }

}
