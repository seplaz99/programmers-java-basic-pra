package com.example.feign_api.controller;

import com.example.feign_api.dto.DataResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DataController {
    private Map<Long, DataResponse> dataStore = new HashMap<>();
    private Long idCounter = 0L;

    @PostConstruct
    private void initDataStore() {
        dataStore.put(++idCounter, new DataResponse(idCounter, "Item 1", 100));
        dataStore.put(++idCounter, new DataResponse(idCounter, "Item 2", 200));
        dataStore.put(++idCounter, new DataResponse(idCounter, "Item 3", 300));
        dataStore.put(++idCounter, new DataResponse(idCounter, "Item 4", 400));
        dataStore.put(++idCounter, new DataResponse(idCounter, "Item 5", 500));
    }
}
