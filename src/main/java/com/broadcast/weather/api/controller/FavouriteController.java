package com.broadcast.weather.api.controller;

import com.broadcast.weather.api.exception.NoFavouritesFound;
import com.broadcast.weather.api.model.Favourite;
import com.broadcast.weather.api.model.FavouriteListResponse;
import com.broadcast.weather.api.model.FavouriteRequest;
import com.broadcast.weather.api.service.FavouriteService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FavouriteController implements FavouriteServiceApi {

    private final FavouriteService favouriteService;

    @Override
    public ResponseEntity<Favourite> createFavourite(FavouriteRequest favouriteRequest) {
        log.info("Request received to save favourite: {}", favouriteRequest.getLocationName());
        return ResponseEntity.status(HttpStatus.CREATED).body(favouriteService.createFavourite(favouriteRequest));
    }

    @Override
    public ResponseEntity<Void> deleteFavourite(Long id) {
        log.info("Request received to delete favourite with id: {}", id);
        favouriteService.deleteFavourite(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<FavouriteListResponse> listFavourites() {
        FavouriteListResponse response = new FavouriteListResponse();

        response.setFavourites(
            Optional.ofNullable(favouriteService.getAllFavourites())
                .filter(favourites -> !favourites.isEmpty())
                .orElseThrow(() -> new NoFavouritesFound("No favourite locations found"))
        );

        return ResponseEntity.ok(response);
    }

}
