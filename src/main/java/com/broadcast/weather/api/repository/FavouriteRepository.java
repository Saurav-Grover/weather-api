package com.broadcast.weather.api.repository;

import com.broadcast.weather.api.entity.FavouriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteRepository extends JpaRepository<FavouriteEntity, Long> {
}
