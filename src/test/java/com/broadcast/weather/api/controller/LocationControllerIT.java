package com.broadcast.weather.api.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.broadcast.weather.api.client.WeatherApiClient;
import com.broadcast.weather.api.client.dto.WeatherApiSearchResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class LocationControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WeatherApiClient weatherApiClient;

    @Test
    void shouldReturn200WithMatchingLocations() throws Exception {
        var searchResult = List.of(
            new WeatherApiSearchResponse("London", "City of London", "United Kingdom", 51.52, -0.11),
            new WeatherApiSearchResponse("Londonderry", "Londonderry", "United Kingdom", 55.0, -7.32)
        );
        when(weatherApiClient.searchLocations("Lond")).thenReturn(searchResult);

        mockMvc.perform(get("/api/v1/locations/search").param("query", "Lond"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("London"))
            .andExpect(jsonPath("$[1].name").value("Londonderry"));
    }

    @Test
    void shouldReturn400WhenQueryTooShort() throws Exception {
        mockMvc.perform(get("/api/v1/locations/search").param("query", "L"))
            .andExpect(status().isBadRequest());
    }
}
