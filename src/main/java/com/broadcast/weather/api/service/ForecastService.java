package com.broadcast.weather.api.service;

import com.broadcast.weather.api.model.ForecastResponse;

public interface ForecastService {
    ForecastResponse getForecast(String location, Integer days);
}
