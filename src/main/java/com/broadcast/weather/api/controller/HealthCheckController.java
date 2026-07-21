package com.broadcast.weather.api.controller;

import com.broadcast.weather.api.model.HealthResponse;
import com.broadcast.weather.api.model.UpstreamApiHealth;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthCheckController implements HealthServiceApi {

    private final CircuitBreakerRegistry circuitBreakerRegistry;

    @Override
    public ResponseEntity<HealthResponse> getHealth() {
        CircuitBreaker cb = circuitBreakerRegistry.circuitBreaker("weatherApi");
        CircuitBreaker.State state = cb.getState();
        float failureRate = cb.getMetrics().getFailureRate();

        UpstreamApiHealth upstreamApiHealth = new UpstreamApiHealth()
            .circuitBreakerState(UpstreamApiHealth.CircuitBreakerStateEnum.fromValue(state.name()))
            .failureRate(failureRate < 0 ? 0f : failureRate);

        HealthResponse response = new HealthResponse(HealthResponse.StatusEnum.UP, OffsetDateTime.now())
            .upstreamApi(upstreamApiHealth);

        return ResponseEntity.ok(response);
    }
}
