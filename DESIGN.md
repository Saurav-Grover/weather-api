# Weather Data Service — Design Document

## Tech Stack Rationale

| Technology      | Rationale                                                          |
|-----------------|--------------------------------------------------------------------|
| Java 21         | Latest LTS with records, pattern matching, virtual threads support |
| Spring Boot 3.3 | Production-ready framework with excellent ecosystem                |
| WebClient       | Non-blocking HTTP client, replacement for RestTemplate             |
| Caffeine        | High-performance in-memory cache for JVM                           |
| Resilience4j    | Lightweight fault tolerance library designed for Java              |
| H2              | Zero-config embedded database for development/demo                 |
| Maven           | Standard build tool with mature dependency management              |

## Architecture

```
┌──────────────────────────────────────────────────────────────────────┐
│                        Weather Data Service                           │
├──────────────────────────────────────────────────────────────────────┤
│                                                                      │
│  ┌───────────────┐  ┌───────────────┐  ┌───────────────────────┐   │
│  │ Weather       │  │ Forecast      │  │ Location / Favourite  │   │
│  │ Controller    │  │ Controller    │  │ Controllers           │   │
│  └───────┬───────┘  └───────┬───────┘  └───────────┬───────────┘   │
│          │                   │                       │               │
│  ┌───────▼───────────────────▼───────────────────────▼───────────┐   │
│  │                      Service Layer                             │   │
│  │  ┌─────────────┐ ┌──────────────┐ ┌────────────────────────┐ │   │
│  │  │WeatherService│ │ForecastService│ │LocationService/FavSvc │ │   │
│  │  └──────┬──────┘ └──────┬───────┘ └────────────┬───────────┘ │   │
│  └─────────┼───────────────┼──────────────────────┼──────────────┘   │
│            │               │                       │                  │
│  ┌─────────▼───────────────▼───────────────────────▼──────────────┐  │
│  │              Cache Layer (Caffeine)                             │  │
│  │  currentWeather: 10min | forecast: 30min | locationSearch: 60min│  │
│  └────────────────────────────┬───────────────────────────────────┘  │
│                               │                                      │
│  ┌────────────────────────────▼───────────────────────────────────┐  │
│  │              WeatherApiClient (WebClient)                       │  │
│  │  ┌────────────────┐  ┌───────────────┐                        │  │
│  │  │ CircuitBreaker │  │  RateLimiter  │                        │  │
│  │  │ (Resilience4j) │  │ (10 req/sec)  │                        │  │
│  │  └────────────────┘  └───────────────┘                        │  │
│  └────────────────────────────┬───────────────────────────────────┘  │
│                               │                                      │
└───────────────────────────────┼──────────────────────────────────────┘
                                │
                    ┌───────────▼───────────┐
                    │   WeatherAPI.com      │
                    │   (upstream provider) │
                    └───────────────────────┘
```

## Caching Strategy

| Cache Name       | TTL        | Max Size    | Rationale                              |
|------------------|------------|-------------|----------------------------------------|
| `currentWeather` | 10 minutes | 500 entries | Weather updates every ~15 min upstream |
| `forecast`       | 30 minutes | 200 entries | Forecasts change less frequently       |
| `locationSearch` | 60 minutes | 300 entries | Location data is essentially static    |

Cache keys use lowercase location names to avoid duplicates (e.g., "London" and "london" hit same cache entry).

## Resilience Patterns

### Circuit Breaker

- **Failure rate threshold**: 50% — trips after half the calls in the sliding window fail
- **Sliding window size**: 10 calls
- **Wait duration in open state**: 30 seconds before attempting half-open
- **Half-open permitted calls**: 3 — tests if upstream has recovered
- **Fallback**: Throws `UpstreamApiException` which returns 502 to the client

### Rate Limiter

- **Limit**: 10 calls per second to WeatherAPI.com
- **Timeout**: 5 seconds — requests wait up to 5s for a permit
- **Purpose**: Protects against exceeding the upstream API's rate limits (free tier has limits)

## Data Flow

### Current Weather Request

1. Client sends `GET /api/v1/weather?location=London`
2. Controller validates input (non-blank)
3. Service checks Caffeine cache for key `"london"`
4. Cache miss → WeatherApiClient makes HTTP call to WeatherAPI.com
5. Resilience4j checks circuit breaker state and rate limiter
6. Upstream response is mapped from `WeatherApiCurrentResponse` → `CurrentWeather`
7. Result is cached and returned to client

### Favourite CRUD

1. Favourites bypass the upstream API entirely
2. CRUD operations go directly to JPA repository (H2 database)
3. No caching needed — database is in-memory and fast

## Error Handling Strategy

| Exception                                 | HTTP Status | Message                                      |
|-------------------------------------------|-------------|----------------------------------------------|
| `LocationNotFoundException`               | 404         | Location-specific message                    |
| `UpstreamApiException`                    | 502         | "Weather service is temporarily unavailable" |
| `MethodArgumentNotValidException`         | 400         | Field-level validation errors                |
| `ConstraintViolationException`            | 400         | Constraint violation details                 |
| `MissingServletRequestParameterException` | 400         | Missing parameter name                       |
| `Exception` (generic)                     | 500         | "An unexpected error occurred"               |

All error responses follow the format:

```json
{
  "status": 502,
  "message": "Weather service is temporarily unavailable",
  "timestamp": "2024-01-15T10:30:00"
}
```

## Future Improvements

Given more time, the following enhancements would improve the service:

1. **Redis Cache** — Replace Caffeine with Redis for distributed caching across multiple instances
2. **PostgreSQL** — Replace H2 with PostgreSQL for persistent favourite storage
4. **Docker** — Containerize with multi-stage Dockerfile
5. **Observability** — Add Micrometer metrics, distributed tracing (OpenTelemetry)
6. **API Versioning** — URI versioning is already in place; add header-based versioning
7. **Security** — Add API key authentication for clients
8. **Async Processing** — Use reactive stack end-to-end instead of `.block()`
9. **User Accounts** — Associate favourites with authenticated users
10. **Alerts** — Allow users to set weather alerts for favourite locations
