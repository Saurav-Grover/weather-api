package com.broadcast.weather.api.controller;

import com.broadcast.weather.api.model.WeatherResponse;
import com.broadcast.weather.api.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WeatherController implements WeatherServiceApi {

    private final WeatherService weatherService;

    @Override
    public ResponseEntity<WeatherResponse> getCurrentWeather(String location) {
        return ResponseEntity.ok(weatherService.getCurrentWeather(location));
    }
}
