package com.broadcast.weather.api.controller;

import com.broadcast.weather.api.model.Location;
import com.broadcast.weather.api.service.LocationService;
import java.util.List;
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
    public ResponseEntity<List<Location>> searchLocations(String query) {
        log.info("Request received to search locations for query: {}", query);
        return ResponseEntity.ok(locationService.searchLocations(query));
    }

}
