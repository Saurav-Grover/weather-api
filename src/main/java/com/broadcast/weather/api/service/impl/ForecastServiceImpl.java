package com.broadcast.weather.api.service.impl;

import com.broadcast.weather.api.client.WeatherApiClient;
import com.broadcast.weather.api.mapper.WeatherMapper;
import com.broadcast.weather.api.model.ForecastResponse;
import com.broadcast.weather.api.service.ForecastService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ForecastServiceImpl implements ForecastService {

    private final WeatherApiClient weatherApiClient;
    private final WeatherMapper mapper;

    @Override
    @Cacheable(value = "forecast", key = "#location.toLowerCase() + '-' + #days")
    public ForecastResponse getForecast(String location, Integer days) {
        log.info("Fetching {} days forecast for {} location.", days, location);
        return mapper.mapToForecastResponse(weatherApiClient.getForecast(location, days));
    }
}
