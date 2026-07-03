package com.example.feign_api.controller;

import com.example.feign_api.dto.DataResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/data")
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

    // /api/data/지정한 id값
    @GetMapping("/{id}")
    public DataResponse getDataById(@PathVariable Long id) {
        DataResponse response = dataStore.get(id);

        if (response == null) {
            throw new RuntimeException("Data not found" + id);
        }

        return response;
    }

}
