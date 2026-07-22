package com.broadcast.weather.api.service.impl;

import com.broadcast.weather.api.client.WeatherApiClient;
import com.broadcast.weather.api.mapper.WeatherMapper;
import com.broadcast.weather.api.model.WeatherResponse;
import com.broadcast.weather.api.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherApiClient weatherApiClient;
    private final WeatherMapper weatherMapper;

    @Override
    @Cacheable(value = "currentWeather", key = "#location.toLowerCase()")
    public WeatherResponse getCurrentWeather(String location) {
        log.info("Fetching current weather for: {}", location);
        return weatherMapper.mapToWeatherResponse(weatherApiClient.getCurrentWeather(location));
    }

}
