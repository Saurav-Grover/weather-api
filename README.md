# Weather Data Service

A Spring Boot microservice that provides weather data by aggregating information from [WeatherAPI.com](https://www.weatherapi.com/). Features include current weather, multi-day forecasts, location search, and favourite locations management.

## Prerequisites

- Java 21
- Maven 3.9+
- WeatherAPI.com API key (free tier available at https://www.weatherapi.com/signup.aspx)

## Getting Started

### 1. Get an API Key

Sign up at [WeatherAPI.com](https://www.weatherapi.com/signup.aspx) to get a free API key.

### 2. Configure Environment

```bash
cp .env.example .env
# Edit .env and replace your-weatherapi-com-key-here with your actual key
export WEATHER_API_KEY=your-actual-key
```

### 3. Run the Application

```bash
mvn spring-boot:run
```

The service starts on `http://localhost:8080`.

### 4. Run Tests

```bash
mvn test
```

## API Documentation

### Current Weather

```bash
curl "http://localhost:8080/api/v1/weather?location=London"
```

Response:
```json
{
  "data": {
    "location": "London",
    "country": "United Kingdom",
    "temperatureCelsius": 15.0,
    "temperatureFahrenheit": 59.0,
    "condition": "Partly cloudy",
    "conditionIcon": "//cdn.weatherapi.com/weather/64x64/day/116.png",
    "windSpeedKph": 12.2,
    "humidity": 72,
    "lastUpdated": "2024-01-15T10:30:00"
  },
  "source": "weatherapi.com"
}
```

### Multi-Day Forecast

```bash
curl "http://localhost:8080/api/v1/forecast?location=London&days=3"
```

Response:
```json
{
  "data": {
    "location": "London",
    "country": "United Kingdom",
    "days": [
      {
        "date": "2024-01-15",
        "maxTempCelsius": 18.0,
        "minTempCelsius": 10.0,
        "condition": "Sunny",
        "conditionIcon": "//cdn.weatherapi.com/weather/64x64/day/113.png",
        "chanceOfRain": 10,
        "maxWindSpeedKph": 20.5
      }
    ]
  },
  "source": "weatherapi.com"
}
```

### Location Search

```bash
curl "http://localhost:8080/api/v1/locations/search?query=Lond"
```

### Save Favourite

```bash
curl -X POST "http://localhost:8080/api/v1/favourites" \
  -H "Content-Type: application/json" \
  -d '{"locationName":"London","country":"United Kingdom","latitude":51.52,"longitude":-0.11}'
```

### List Favourites

```bash
curl "http://localhost:8080/api/v1/favourites"
```

### Delete Favourite

```bash
curl -X DELETE "http://localhost:8080/api/v1/favourites/1"
```

### Health Check

```bash
curl "http://localhost:8080/api/v1/health"
```

## Architecture Overview

```
┌─────────────┐     ┌──────────────┐     ┌────────────────┐     ┌─────────────────┐
│  Controller │────▶│   Service    │────▶│ WeatherApiClient│────▶│ WeatherAPI.com  │
│   Layer     │     │   Layer      │     │  (WebClient)    │     │  (upstream)     │
└─────────────┘     └──────────────┘     └────────────────┘     └─────────────────┘
                           │
                    ┌──────┴──────┐
                    │  Caffeine   │
                    │   Cache     │
                    └─────────────┘
```

- **Controllers**: REST endpoints with Jakarta Validation
- **Services**: Business logic with `@Cacheable` annotations
- **Client**: WebClient-based HTTP client with Resilience4j circuit breaker and rate limiter
- **Repository**: JPA repository for favourite locations (H2 in-memory)

## Key Features

- In-memory caching with Caffeine (10min current weather, 30min forecasts, 60min location search)
- Circuit breaker pattern with Resilience4j (50% failure threshold, 30s open state)
- Rate limiting to upstream API (10 calls/second)
- Consistent error handling with structured error responses
- H2 in-memory database for favourites (easily swappable to PostgreSQL)
