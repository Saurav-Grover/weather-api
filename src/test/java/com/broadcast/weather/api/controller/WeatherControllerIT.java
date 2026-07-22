package com.broadcast.weather.api.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.broadcast.weather.api.client.WeatherApiClient;
import com.broadcast.weather.api.client.dto.WeatherApiCurrentResponse;
import com.broadcast.weather.api.exception.UpstreamApiException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WeatherApiClient weatherApiClient;

    @Test
    void shouldReturn200WithValidWeatherData() throws Exception {
        var location = new WeatherApiCurrentResponse.LocationDto(
            "London", "City of London", "United Kingdom", 51.52, -0.11, "2024-01-15 12:00");
        var condition = new WeatherApiCurrentResponse.ConditionDto("Sunny", "//cdn.icon.png");
        var current = new WeatherApiCurrentResponse.CurrentDto(18.0, 64.4, condition, 10.0, 65, 17.0, 62.6, 3.0,
            "2024-01-15 12:00");
        when(weatherApiClient.getCurrentWeather("London"))
            .thenReturn(new WeatherApiCurrentResponse(location, current));

        mockMvc.perform(get("/api/v1/weather").param("location", "London"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.location").value("London"))
            .andExpect(jsonPath("$.data.temperatureCelsius").value(18.0))
            .andExpect(jsonPath("$.source").value("weatherapi.com"));
    }

    @Test
    void shouldReturn400WhenLocationParamMissing() throws Exception {
        mockMvc.perform(get("/api/v1/weather"))
            .andDo(print())
            .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn502WhenUpstreamIsDown() throws Exception {
        when(weatherApiClient.getCurrentWeather("London"))
            .thenThrow(new UpstreamApiException("Service unavailable"));

        mockMvc.perform(get("/api/v1/weather").param("location", "London"))
            .andDo(print())
            .andExpect(status().isBadGateway())
            .andExpect(jsonPath("$.status").value(502));
    }
}
