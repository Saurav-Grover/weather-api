package com.broadcast.weather.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import com.broadcast.weather.api.client.WeatherApiClient;
import com.broadcast.weather.api.client.dto.WeatherApiCurrentResponse;
import com.broadcast.weather.api.exception.UpstreamApiException;
import com.broadcast.weather.api.mapper.WeatherMapper;
import com.broadcast.weather.api.model.CurrentWeather;
import com.broadcast.weather.api.model.WeatherResponse;
import com.broadcast.weather.api.service.impl.WeatherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @Mock
    private WeatherApiClient weatherApiClient;

    @Mock
    private WeatherMapper weatherMapper;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    private WeatherApiCurrentResponse mockApiResponse;
    private WeatherResponse mockWeatherResponse;

    @BeforeEach
    void setUp() {
        var location = new WeatherApiCurrentResponse.LocationDto(
            "London", "City of London", "United Kingdom", 51.52, -0.11, "2024-01-15 10:30"
        );
        var condition = new WeatherApiCurrentResponse.ConditionDto("Partly cloudy", "//cdn.weatherapi.com/icon.png");
        var current = new WeatherApiCurrentResponse.CurrentDto(15.0, 59.0, condition, 12.2, 72, 14.0, 57.2, 3.0,
            "2024-01-15 10:30");
        mockApiResponse = new WeatherApiCurrentResponse(location, current);

        var data = new CurrentWeather();
        data.setLocation("London");
        data.setCountry("United Kingdom");
        data.setTemperatureCelsius(15.0);
        data.setTemperatureFahrenheit(59.0);
        data.setCondition("Partly cloudy");
        data.setWindSpeedKph(12.2);
        data.setHumidity(72);
        mockWeatherResponse = new WeatherResponse().data(data).source("weatherapi.com");
    }

    @Test
    void shouldReturnCurrentWeatherForValidLocation() {
        when(weatherApiClient.getCurrentWeather("London")).thenReturn(mockApiResponse);
        when(weatherMapper.mapToWeatherResponse(mockApiResponse)).thenReturn(mockWeatherResponse);

        WeatherResponse result = weatherService.getCurrentWeather("London");

        assertThat(result.getData().getLocation()).isEqualTo("London");
        assertThat(result.getData().getCountry()).isEqualTo("United Kingdom");
        assertThat(result.getData().getTemperatureCelsius()).isEqualTo(15.0);
        assertThat(result.getData().getTemperatureFahrenheit()).isEqualTo(59.0);
        assertThat(result.getData().getCondition()).isEqualTo("Partly cloudy");
        assertThat(result.getData().getWindSpeedKph()).isEqualTo(12.2);
        assertThat(result.getData().getHumidity()).isEqualTo(72);
    }

    @Test
    void shouldReturnSameResultOnSecondCallWithSameLocation() {
        when(weatherApiClient.getCurrentWeather("London")).thenReturn(mockApiResponse);
        when(weatherMapper.mapToWeatherResponse(mockApiResponse)).thenReturn(mockWeatherResponse);

        WeatherResponse first = weatherService.getCurrentWeather("London");
        WeatherResponse second = weatherService.getCurrentWeather("London");

        assertThat(first.getData().getLocation()).isEqualTo(second.getData().getLocation());
        assertThat(first.getData().getTemperatureCelsius()).isEqualTo(second.getData().getTemperatureCelsius());
    }

    @Test
    void shouldThrowWhenUpstreamFails() {
        when(weatherApiClient.getCurrentWeather("InvalidCity"))
            .thenThrow(new UpstreamApiException("Service unavailable"));

        assertThatThrownBy(() -> weatherService.getCurrentWeather("InvalidCity"))
            .isInstanceOf(UpstreamApiException.class)
            .hasMessageContaining("Service unavailable");
    }
}
