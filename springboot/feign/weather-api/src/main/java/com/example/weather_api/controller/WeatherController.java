package com.example.weather_api.controller;

import com.example.weather_api.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/weather")
    public List<String> weather() {
        // 서울의 기상청 격자 좌표는 nx=60, ny=127 입니다.
        return weatherService.getReadableWeather(60, 127);
    }
}
