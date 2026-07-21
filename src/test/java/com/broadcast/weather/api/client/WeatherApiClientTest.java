package com.broadcast.weather.api.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.broadcast.weather.api.client.dto.WeatherApiCurrentResponse;
import com.broadcast.weather.api.client.dto.WeatherApiForecastResponse;
import com.broadcast.weather.api.client.dto.WeatherApiSearchResponse;
import com.broadcast.weather.api.exception.LocationNotFoundException;
import java.io.IOException;
import java.util.List;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

class WeatherApiClientTest {

    private MockWebServer mockWebServer;
    private WeatherApiClient weatherApiClient;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        WebClient webClient = WebClient.builder()
            .baseUrl(mockWebServer.url("/").toString())
            .build();

        weatherApiClient = new WeatherApiClient(webClient, "test-api-key", 5000);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void shouldReturnCurrentWeatherForValidLocation() {
        String responseBody = """
            {
              "location": {
                "name": "London",
                "region": "City of London",
                "country": "United Kingdom",
                "lat": 51.52,
                "lon": -0.11,
                "localtime": "2024-01-15 10:30"
              },
              "current": {
                "temp_c": 15.0,
                "temp_f": 59.0,
                "condition": {
                  "text": "Partly cloudy",
                  "icon": "//cdn.weatherapi.com/weather/64x64/day/116.png"
                },
                "wind_kph": 12.2,
                "humidity": 72,
                "feelslike_c": 14.0,
                "feelslike_f": 57.2,
                "uv": 3.0,
                "last_updated": "2024-01-15 10:30"
              }
            }
            """;
        mockWebServer.enqueue(new MockResponse()
            .setBody(responseBody)
            .addHeader("Content-Type", "application/json"));

        WeatherApiCurrentResponse result = weatherApiClient.getCurrentWeather("London");

        assertThat(result.location().name()).isEqualTo("London");
        assertThat(result.current().tempC()).isEqualTo(15.0);
        assertThat(result.current().humidity()).isEqualTo(72);
    }

    @Test
    void shouldReturnForecastForValidLocation() {
        String responseBody = """
            {
              "location": {
                "name": "London",
                "region": "City of London",
                "country": "United Kingdom",
                "lat": 51.52,
                "lon": -0.11,
                "localtime": "2024-01-15 12:00"
              },
              "current": {
                "temp_c": 18.0,
                "temp_f": 64.4,
                "condition": { "text": "Sunny", "icon": "//cdn.weatherapi.com/weather/64x64/day/113.png" },
                "wind_kph": 10.0,
                "humidity": 60,
                "feelslike_c": 17.0,
                "feelslike_f": 62.6,
                "uv": 4.0,
                "last_updated": "2024-01-15 12:00"
              },
              "forecast": {
                "forecastday": [
                  {
                    "date": "2024-01-15",
                    "day": {
                      "maxtemp_c": 18.0,
                      "mintemp_c": 10.0,
                      "avgtemp_c": 14.0,
                      "maxwind_kph": 20.5,
                      "condition": {
                        "text": "Sunny",
                        "icon": "//cdn.weatherapi.com/weather/64x64/day/113.png"
                      },
                      "daily_chance_of_rain": 10
                    }
                  }
                ]
              }
            }
            """;
        mockWebServer.enqueue(new MockResponse()
            .setBody(responseBody)
            .addHeader("Content-Type", "application/json"));

        WeatherApiForecastResponse result = weatherApiClient.getForecast("London", 1);

        assertThat(result.location().name()).isEqualTo("London");
        assertThat(result.forecast().forecastDay()).hasSize(1);
        assertThat(result.forecast().forecastDay().get(0).day().maxTempC()).isEqualTo(18.0);
    }

    @Test
    void shouldReturnSearchResults() {
        String responseBody = """
            [
              {
                "name": "London",
                "region": "City of London, Greater London",
                "country": "United Kingdom",
                "lat": 51.52,
                "lon": -0.11
              },
              {
                "name": "Londonderry",
                "region": "Londonderry",
                "country": "United Kingdom",
                "lat": 55.0,
                "lon": -7.32
              }
            ]
            """;
        mockWebServer.enqueue(new MockResponse()
            .setBody(responseBody)
            .addHeader("Content-Type", "application/json"));

        List<WeatherApiSearchResponse> result = weatherApiClient.searchLocations("London");

        assertThat(result).hasSize(2);
        assertThat(result.get(0).name()).isEqualTo("London");
        assertThat(result.get(1).name()).isEqualTo("Londonderry");
    }

    @Test
    void shouldThrowLocationNotFoundFor400Response() {
        mockWebServer.enqueue(new MockResponse()
            .setResponseCode(400)
            .setBody("{\"error\":{\"message\":\"No matching location found.\"}}")
            .addHeader("Content-Type", "application/json"));

        assertThatThrownBy(() -> weatherApiClient.getCurrentWeather("XYZ123"))
            .isInstanceOf(LocationNotFoundException.class);
    }
}
