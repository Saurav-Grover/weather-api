package com.broadcast.weather.api.mapper;

import com.broadcast.weather.api.client.dto.WeatherApiCurrentResponse;
import com.broadcast.weather.api.client.dto.WeatherApiForecastResponse;
import com.broadcast.weather.api.client.dto.WeatherApiSearchResponse;
import com.broadcast.weather.api.model.DailyForecast;
import com.broadcast.weather.api.model.ForecastResponse;
import com.broadcast.weather.api.model.Location;
import com.broadcast.weather.api.model.WeatherResponse;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface WeatherMapper {

    @Mapping(target = "data.location", source = "location.name")
    @Mapping(target = "data.country", source = "location.country")
    @Mapping(target = "data.temperatureCelsius", source = "current.tempC")
    @Mapping(target = "data.temperatureFahrenheit", source = "current.tempF")
    @Mapping(target = "data.condition", source = "current.condition.text")
    @Mapping(target = "data.conditionIcon", source = "current.condition.icon")
    @Mapping(target = "data.windSpeedKph", source = "current.windKph")
    @Mapping(target = "data.humidity", source = "current.humidity")
    @Mapping(target = "data.lastUpdated", source = "current.lastUpdated", qualifiedByName = "parseLastUpdated")
    @Mapping(target = "source", ignore = true)
    WeatherResponse mapToWeatherResponse(WeatherApiCurrentResponse response);

    @Mapping(target = "data.location", source = "location.name")
    @Mapping(target = "data.country", source = "location.country")
    @Mapping(target = "data.days", source = "forecast.forecastDay")
    @Mapping(target = "source", ignore = true)
    ForecastResponse mapToForecastResponse(WeatherApiForecastResponse response);

    @Mapping(target = "date", expression = "java(java.time.LocalDate.parse(forecastDayDto.date()))")
    @Mapping(target = "maxTempCelsius", source = "day.maxTempC")
    @Mapping(target = "minTempCelsius", source = "day.minTempC")
    @Mapping(target = "condition", source = "day.condition.text")
    @Mapping(target = "conditionIcon", source = "day.condition.icon")
    @Mapping(target = "chanceOfRain", source = "day.dailyChanceOfRain")
    @Mapping(target = "maxWindSpeedKph", source = "day.maxWindKph")
    DailyForecast map(WeatherApiForecastResponse.ForecastDayDto forecastDayDto);

    List<Location> mapToLocationList(List<WeatherApiSearchResponse> responses);

    @Mapping(target = "latitude", source = "lat")
    @Mapping(target = "longitude", source = "lon")
    Location map(WeatherApiSearchResponse response);


    @Named("parseLastUpdated")
    default OffsetDateTime parseLastUpdated(String dateTimeStr) {
        try {
            return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                .atOffset(ZoneOffset.UTC);
        } catch (Exception e) {
            return OffsetDateTime.now();
        }
    }
}
