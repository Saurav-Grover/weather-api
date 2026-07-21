package com.broadcast.weather.api.service.impl;

import com.broadcast.weather.api.entity.FavouriteEntity;
import com.broadcast.weather.api.exception.LocationNotFoundException;
import com.broadcast.weather.api.mapper.FavouriteMapper;
import com.broadcast.weather.api.model.Favourite;
import com.broadcast.weather.api.model.FavouriteRequest;
import com.broadcast.weather.api.repository.FavouriteRepository;
import com.broadcast.weather.api.service.FavouriteService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavouriteServiceImpl implements FavouriteService {

    private final FavouriteRepository favouriteRepository;
    private final FavouriteMapper mapper;

    public Favourite createFavourite(FavouriteRequest request) {
        log.info("Saving favourite: {}", request.getLocationName());
        FavouriteEntity entity = mapper.mapToFavouriteEntity(request);
        FavouriteEntity saved = favouriteRepository.save(entity);
        return mapper.mapToFavourite(saved);
    }

    public List<Favourite> getAllFavourites() {
        return favouriteRepository.findAll().stream()
            .map(mapper::mapToFavourite)
            .toList();
    }

    public void deleteFavourite(Long id) {
        if (!favouriteRepository.existsById(id)) {
            throw new LocationNotFoundException("Favourite not found with id: " + id);
        }
        favouriteRepository.deleteById(id);
        log.info("Deleted favourite with id: {}", id);
    }

}
