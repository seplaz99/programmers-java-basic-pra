package com.example.feign_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DataResponse {
    private Long id;
    private String name;
    private int value;
}
