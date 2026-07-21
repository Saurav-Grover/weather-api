package com.broadcast.weather.api.controller;

import com.broadcast.weather.api.model.LocationSearchResponse;
import com.broadcast.weather.api.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LocationController implements LocationServiceApi {

    private final LocationService locationService;

    @Override
    public ResponseEntity<LocationSearchResponse> searchLocations(String query) {
        log.info("Request received to search locations for query: {}", query);
        LocationSearchResponse response = new LocationSearchResponse();
        response.setLocations(locationService.searchLocations(query));
        return ResponseEntity.ok(response);
    }

}
