package com.broadcast.weather.api.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherApiCurrentResponse(
    LocationDto location,
    CurrentDto current
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record LocationDto(
        String name,
        String region,
        String country,
        double lat,
        double lon,
        String localtime
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record CurrentDto(
        @JsonProperty("temp_c") double tempC,
        @JsonProperty("temp_f") double tempF,
        ConditionDto condition,
        @JsonProperty("wind_kph") double windKph,
        int humidity,
        @JsonProperty("feelslike_c") double feelsLikeC,
        @JsonProperty("feelslike_f") double feelsLikeF,
        double uv,
        @JsonProperty("last_updated") String lastUpdated
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ConditionDto(
        String text,
        String icon
    ) {
    }
}
