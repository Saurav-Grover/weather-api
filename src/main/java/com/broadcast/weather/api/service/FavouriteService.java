package com.broadcast.weather.api.service;

import com.broadcast.weather.api.model.Favourite;
import com.broadcast.weather.api.model.FavouriteRequest;
import java.util.List;

public interface FavouriteService {
    Favourite createFavourite(FavouriteRequest favouriteRequest);

    List<Favourite> getAllFavourites();

    void deleteFavourite(Long id);
}
