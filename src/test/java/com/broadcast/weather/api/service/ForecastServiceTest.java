package com.broadcast.weather.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.broadcast.weather.api.client.WeatherApiClient;
import com.broadcast.weather.api.client.dto.WeatherApiCurrentResponse;
import com.broadcast.weather.api.client.dto.WeatherApiForecastResponse;
import com.broadcast.weather.api.mapper.WeatherMapper;
import com.broadcast.weather.api.model.ForecastData;
import com.broadcast.weather.api.model.ForecastResponse;
import com.broadcast.weather.api.service.impl.ForecastServiceImpl;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ForecastServiceTest {

    @Mock
    private WeatherApiClient weatherApiClient;

    @Mock
    private WeatherMapper mapper;

    @InjectMocks
    private ForecastServiceImpl forecastService;

    private WeatherApiForecastResponse mockResponse;

    @BeforeEach
    void setUp() {
        var location = new WeatherApiCurrentResponse.LocationDto(
            "London", "City of London", "United Kingdom", 51.52, -0.11, "2024-01-15 12:00"
        );
        var condition = new WeatherApiCurrentResponse.ConditionDto("Sunny", "//cdn.weatherapi.com/icon.png");
        var current = new WeatherApiCurrentResponse.CurrentDto(20.0, 68.0, condition, 15.0, 60, 19.0, 66.2, 4.0,
            "2024-01-15 12:00");
        var day = new WeatherApiForecastResponse.DayDto(20.0, 12.0, 16.0, 15.5, condition, 30);
        var forecastDay = new WeatherApiForecastResponse.ForecastDayDto("2024-01-15", day);
        var forecast = new WeatherApiForecastResponse.ForecastDto(List.of(forecastDay));
        mockResponse = new WeatherApiForecastResponse(location, current, forecast);
    }

    @Test
    void shouldReturnForecastForValidLocationAndDays() {
        var expectedData = new ForecastData("London", "United Kingdom", List.of());
        var expected = new ForecastResponse().data(expectedData);
        when(weatherApiClient.getForecast("London", 3)).thenReturn(mockResponse);
        when(mapper.mapToForecastResponse(mockResponse)).thenReturn(expected);

        ForecastResponse result = forecastService.getForecast("London", 3);

        assertThat(result.getData().getLocation()).isEqualTo("London");
        assertThat(result.getData().getCountry()).isEqualTo("United Kingdom");
    }

    @Test
    void shouldDefaultToThreeDays() {
        var expected = new ForecastResponse().data(new ForecastData("London", "United Kingdom", List.of()));
        when(weatherApiClient.getForecast("London", 3)).thenReturn(mockResponse);
        when(mapper.mapToForecastResponse(mockResponse)).thenReturn(expected);

        ForecastResponse result = forecastService.getForecast("London", 3);

        assertThat(result).isNotNull();
        assertThat(result.getData().getLocation()).isEqualTo("London");
    }

    @Test
    void shouldReturnForecastWithMultipleDays() {
        var location = new WeatherApiCurrentResponse.LocationDto(
            "London", "City of London", "United Kingdom", 51.52, -0.11, "2024-01-15 12:00"
        );
        var condition = new WeatherApiCurrentResponse.ConditionDto("Rainy", "//cdn.weatherapi.com/rain.png");
        var current = new WeatherApiCurrentResponse.CurrentDto(18.0, 64.4, condition, 20.0, 80, 17.0, 62.6, 2.0,
            "2024-01-15 12:00");
        var day1 = new WeatherApiForecastResponse.DayDto(18.0, 10.0, 14.0, 20.0, condition, 80);
        var day2 = new WeatherApiForecastResponse.DayDto(22.0, 14.0, 18.0, 18.0, condition, 20);
        var forecast = new WeatherApiForecastResponse.ForecastDto(List.of(
            new WeatherApiForecastResponse.ForecastDayDto("2024-01-15", day1),
            new WeatherApiForecastResponse.ForecastDayDto("2024-01-16", day2)
        ));
        var multiDayResponse = new WeatherApiForecastResponse(location, current, forecast);
        var expectedData = new ForecastData("London", "United Kingdom",
            List.of(new com.broadcast.weather.api.model.DailyForecast(),
                new com.broadcast.weather.api.model.DailyForecast()));
        var expected = new ForecastResponse().data(expectedData);
        when(weatherApiClient.getForecast("London", 2)).thenReturn(multiDayResponse);
        when(mapper.mapToForecastResponse(multiDayResponse)).thenReturn(expected);

        ForecastResponse result = forecastService.getForecast("London", 2);

        assertThat(result.getData().getDays()).hasSize(2);
    }
}
