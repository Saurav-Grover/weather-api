package com.broadcast.weather.api.client;

import com.broadcast.weather.api.client.dto.WeatherApiCurrentResponse;
import com.broadcast.weather.api.client.dto.WeatherApiForecastResponse;
import com.broadcast.weather.api.client.dto.WeatherApiSearchResponse;
import com.broadcast.weather.api.exception.LocationNotFoundException;
import com.broadcast.weather.api.exception.UpstreamApiException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import java.time.Duration;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WeatherApiClient {

    private static final Logger log = LoggerFactory.getLogger(WeatherApiClient.class);

    private final WebClient webClient;
    private final String apiKey;
    private final int timeoutMs;

    public WeatherApiClient(WebClient weatherApiWebClient,
                            @Value("${weather.api.key}") String apiKey,
                            @Value("${weather.api.timeout-ms}") int timeoutMs) {
        this.webClient = weatherApiWebClient;
        this.apiKey = apiKey;
        this.timeoutMs = timeoutMs;
    }

    @CircuitBreaker(name = "weatherApi", fallbackMethod = "getCurrentWeatherFallback")
    @RateLimiter(name = "weatherApi")
    public WeatherApiCurrentResponse getCurrentWeather(String location) {
        log.debug("Fetching current weather for location: {}", location);
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/current.json")
                .queryParam("key", apiKey)
                .queryParam("q", location)
                .build())
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError, response ->
                Mono.error(new LocationNotFoundException("Location not found: " + location)))
            .onStatus(HttpStatusCode::is5xxServerError, response ->
                Mono.error(new UpstreamApiException("Upstream API returned server error")))
            .bodyToMono(WeatherApiCurrentResponse.class)
            .block(Duration.ofMillis(timeoutMs));
    }

    @CircuitBreaker(name = "weatherApi", fallbackMethod = "getForecastFallback")
    @RateLimiter(name = "weatherApi")
    public WeatherApiForecastResponse getForecast(String location, int days) {
        log.debug("Fetching forecast for location: {}, days: {}", location, days);
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/forecast.json")
                .queryParam("key", apiKey)
                .queryParam("q", location)
                .queryParam("days", days)
                .build())
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError, response ->
                Mono.error(new LocationNotFoundException("Location not found: " + location)))
            .onStatus(HttpStatusCode::is5xxServerError, response ->
                Mono.error(new UpstreamApiException("Upstream API returned server error")))
            .bodyToMono(WeatherApiForecastResponse.class)
            .block(Duration.ofMillis(timeoutMs));
    }

    @CircuitBreaker(name = "weatherApi", fallbackMethod = "searchLocationsFallback")
    @RateLimiter(name = "weatherApi")
    public List<WeatherApiSearchResponse> searchLocations(String query) {
        log.debug("Searching locations with query: {}", query);
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/search.json")
                .queryParam("key", apiKey)
                .queryParam("q", query)
                .build())
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError, response ->
                Mono.error(new UpstreamApiException("Invalid search query")))
            .onStatus(HttpStatusCode::is5xxServerError, response ->
                Mono.error(new UpstreamApiException("Upstream API returned server error")))
            .bodyToMono(WeatherApiSearchResponse[].class)
            .map(List::of)
            .block(Duration.ofMillis(timeoutMs));
    }

    @SuppressWarnings("unused")
    private WeatherApiCurrentResponse getCurrentWeatherFallback(String location, Throwable t) {
        if (t instanceof LocationNotFoundException) throw (LocationNotFoundException) t;
        log.warn("Circuit breaker fallback triggered for getCurrentWeather, location: {}, cause: {}", location, t.getMessage());
        throw new UpstreamApiException("Weather service unavailable. Please try again later.", t);
    }

    @SuppressWarnings("unused")
    private WeatherApiForecastResponse getForecastFallback(String location, int days, Throwable t) {
        if (t instanceof LocationNotFoundException) throw (LocationNotFoundException) t;
        log.warn("Circuit breaker fallback triggered for getForecast, location: {}, days: {}, cause: {}", location, days, t.getMessage());
        throw new UpstreamApiException("Forecast service unavailable. Please try again later.", t);
    }

    @SuppressWarnings("unused")
    private List<WeatherApiSearchResponse> searchLocationsFallback(String query, Throwable t) {
        if (t instanceof UpstreamApiException) throw (UpstreamApiException) t;
        log.warn("Circuit breaker fallback triggered for searchLocations, query: {}, cause: {}", query, t.getMessage());
        throw new UpstreamApiException("Location search service unavailable. Please try again later.", t);
    }
}
