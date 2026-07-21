package com.broadcast.weather.api.service;

import com.broadcast.weather.api.model.WeatherResponse;

public interface WeatherService {

    WeatherResponse getCurrentWeather(String location);
}
