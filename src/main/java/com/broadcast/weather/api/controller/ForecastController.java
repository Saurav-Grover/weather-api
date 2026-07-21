package com.broadcast.weather.api.controller;

import com.broadcast.weather.api.model.ForecastResponse;
import com.broadcast.weather.api.service.ForecastService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ForecastController implements ForecastServiceApi {

    private final ForecastService forecastService;

    @Override
    public ResponseEntity<ForecastResponse> getForecast(String location, Integer days) {
        log.info("Request received to fetch forecast for location: {}, days: {}", location, days);
        return ResponseEntity.ok(forecastService.getForecast(location, days));
    }
}
