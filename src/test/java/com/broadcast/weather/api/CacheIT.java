package com.broadcast.weather.api;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.broadcast.weather.api.client.WeatherApiClient;
import com.broadcast.weather.api.client.dto.WeatherApiCurrentResponse;
import com.broadcast.weather.api.client.dto.WeatherApiForecastResponse;
import com.broadcast.weather.api.client.dto.WeatherApiSearchResponse;
import com.broadcast.weather.api.service.ForecastService;
import com.broadcast.weather.api.service.LocationService;
import com.broadcast.weather.api.service.WeatherService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class CacheIT {

    private static final String LOCATION = "London";
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private ForecastService forecastService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private CacheManager cacheManager;
    @MockitoBean
    private WeatherApiClient weatherApiClient;

    private WeatherApiCurrentResponse mockCurrentResponse() {
        var location =
            new WeatherApiCurrentResponse.LocationDto(LOCATION, "City of London", "United Kingdom", 51.52, -0.11,
                "2024-01-15 12:00");
        var condition = new WeatherApiCurrentResponse.ConditionDto("Sunny", "//cdn.icon.png");
        var current = new WeatherApiCurrentResponse.CurrentDto(18.0, 64.4, condition, 10.0, 65, 17.0, 62.6, 3.0,
            "2024-01-15 12:00");
        return new WeatherApiCurrentResponse(location, current);
    }

    private WeatherApiForecastResponse mockForecastResponse() {
        var location =
            new WeatherApiCurrentResponse.LocationDto(LOCATION, "City of London", "United Kingdom", 51.52, -0.11,
                "2024-01-15 12:00");
        var condition = new WeatherApiCurrentResponse.ConditionDto("Sunny", "//cdn.icon.png");
        var day = new WeatherApiForecastResponse.DayDto(18.0, 10.0, 14.0, 20.0, condition, 5);
        var forecastDay = new WeatherApiForecastResponse.ForecastDayDto("2024-01-15", day);
        var forecast = new WeatherApiForecastResponse.ForecastDto(List.of(forecastDay));
        var current = new WeatherApiCurrentResponse.CurrentDto(18.0, 64.4, condition, 10.0, 65, 17.0, 62.6, 3.0,
            "2024-01-15 12:00");
        return new WeatherApiForecastResponse(location, current, forecast);
    }

    @BeforeEach
    void clearCaches() {
        cacheManager.getCacheNames().forEach(name -> cacheManager.getCache(name).clear());
    }

    @Test
    void shouldCacheCurrentWeather() {
        when(weatherApiClient.getCurrentWeather(LOCATION)).thenReturn(mockCurrentResponse());

        weatherService.getCurrentWeather(LOCATION);
        weatherService.getCurrentWeather(LOCATION);
        weatherService.getCurrentWeather(LOCATION);

        verify(weatherApiClient, times(1)).getCurrentWeather(LOCATION);
    }

    @Test
    void shouldCacheForecast() {
        when(weatherApiClient.getForecast(LOCATION, 3)).thenReturn(mockForecastResponse());

        forecastService.getForecast(LOCATION, 3);
        forecastService.getForecast(LOCATION, 3);
        forecastService.getForecast(LOCATION, 3);

        verify(weatherApiClient, times(1)).getForecast(LOCATION, 3);
    }

    @Test
    void shouldCacheLocationSearch() {
        var searchResponse = new WeatherApiSearchResponse(LOCATION, "City of London", "United Kingdom", 51.52, -0.11);
        when(weatherApiClient.searchLocations(LOCATION)).thenReturn(List.of(searchResponse));

        locationService.searchLocations(LOCATION);
        locationService.searchLocations(LOCATION);
        locationService.searchLocations(LOCATION);

        verify(weatherApiClient, times(1)).searchLocations(LOCATION);
    }

    @Test
    void shouldCacheSeparatelyForDifferentLocations() {
        when(weatherApiClient.getCurrentWeather(LOCATION)).thenReturn(mockCurrentResponse());
        when(weatherApiClient.getCurrentWeather("Paris")).thenReturn(mockCurrentResponse());

        weatherService.getCurrentWeather(LOCATION);
        weatherService.getCurrentWeather(LOCATION);
        weatherService.getCurrentWeather("Paris");
        weatherService.getCurrentWeather("Paris");

        verify(weatherApiClient, times(1)).getCurrentWeather(LOCATION);
        verify(weatherApiClient, times(1)).getCurrentWeather("Paris");
    }

    @Test
    void shouldCacheSeparatelyForDifferentDays() {
        when(weatherApiClient.getForecast(LOCATION, 3)).thenReturn(mockForecastResponse());
        when(weatherApiClient.getForecast(LOCATION, 5)).thenReturn(mockForecastResponse());

        forecastService.getForecast(LOCATION, 3);
        forecastService.getForecast(LOCATION, 3);
        forecastService.getForecast(LOCATION, 5);
        forecastService.getForecast(LOCATION, 5);

        verify(weatherApiClient, times(1)).getForecast(LOCATION, 3);
        verify(weatherApiClient, times(1)).getForecast(LOCATION, 5);
    }
}
