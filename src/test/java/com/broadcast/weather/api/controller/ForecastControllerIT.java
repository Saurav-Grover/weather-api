package com.broadcast.weather.api.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.broadcast.weather.api.client.WeatherApiClient;
import com.broadcast.weather.api.client.dto.WeatherApiCurrentResponse;
import com.broadcast.weather.api.client.dto.WeatherApiForecastResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ForecastControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WeatherApiClient weatherApiClient;

    @Test
    void shouldReturn200WithForecastData() throws Exception {
        var location = new WeatherApiCurrentResponse.LocationDto(
            "London", "City of London", "United Kingdom", 51.52, -0.11, "2024-01-14 12:00");
        var condition = new WeatherApiCurrentResponse.ConditionDto("Cloudy", "//cdn.icon.png");
        var current = new WeatherApiCurrentResponse.CurrentDto(15.0, 59.0, condition, 12.0, 70, 14.0, 57.2, 2.0,
            "2024-01-14 12:00");
        var day = new WeatherApiForecastResponse.DayDto(20.0, 12.0, 15.0, 40.0, condition, 30);
        var forecastDay = new WeatherApiForecastResponse.ForecastDayDto("2024-01-15", day);
        var forecast = new WeatherApiForecastResponse.ForecastDto(List.of(forecastDay));
        var response = new WeatherApiForecastResponse(location, current, forecast);

        when(weatherApiClient.getForecast("London", 3)).thenReturn(response);

        mockMvc.perform(get("/api/v1/forecast")
                .param("location", "London")
                .param("days", "3"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.location").value("London"))
            .andExpect(jsonPath("$.data.days[0].maxTempCelsius").value(20.0))
            .andExpect(jsonPath("$.source").value("weatherapi.com"));
    }

    @Test
    void shouldReturn400WhenLocationMissing() throws Exception {
        mockMvc.perform(get("/api/v1/forecast"))
            .andExpect(status().isBadRequest());
    }
}
