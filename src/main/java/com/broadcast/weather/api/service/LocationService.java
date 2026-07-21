package com.broadcast.weather.api.service;

import com.broadcast.weather.api.model.Location;
import java.util.List;

public interface LocationService {

    List<Location> searchLocations(String query);
}
