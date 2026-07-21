package com.broadcast.weather.api.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherApiForecastResponse(
    WeatherApiCurrentResponse.LocationDto location,
    WeatherApiCurrentResponse.CurrentDto current,
    ForecastDto forecast
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ForecastDto(
        @JsonProperty("forecastday") List<ForecastDayDto> forecastDay
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ForecastDayDto(
        String date,
        DayDto day
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record DayDto(
        @JsonProperty("maxtemp_c") double maxTempC,
        @JsonProperty("mintemp_c") double minTempC,
        @JsonProperty("avgtemp_c") double avgTempC,
        @JsonProperty("maxwind_kph") double maxWindKph,
        WeatherApiCurrentResponse.ConditionDto condition,
        @JsonProperty("daily_chance_of_rain") int dailyChanceOfRain
    ) {
    }
}
