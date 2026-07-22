package com.broadcast.weather.api.service.impl;

import com.broadcast.weather.api.client.WeatherApiClient;
import com.broadcast.weather.api.mapper.WeatherMapper;
import com.broadcast.weather.api.model.Location;
import com.broadcast.weather.api.service.LocationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationServiceImpl implements LocationService {

    private final WeatherApiClient weatherApiClient;
    private final WeatherMapper mapper;

    @Override
    @Cacheable(value = "locationSearch", key = "#query.toLowerCase()")
    public List<Location> searchLocations(String query) {
        log.info("Searching locations for query: {}", query);
        return mapper.mapToLocationList(weatherApiClient.searchLocations(query));
    }
}
