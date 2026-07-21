package com.broadcast.weather.api.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherApiSearchResponse(
    String name,
    String region,
    String country,
    double lat,
    double lon
) {
}
