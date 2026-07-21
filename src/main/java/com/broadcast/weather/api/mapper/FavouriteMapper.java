package com.broadcast.weather.api.mapper;

import com.broadcast.weather.api.entity.FavouriteEntity;
import com.broadcast.weather.api.model.Favourite;
import com.broadcast.weather.api.model.FavouriteRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface FavouriteMapper {

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    FavouriteEntity mapToFavouriteEntity(FavouriteRequest request);

    @Mapping(target = "createdAt", expression = "java(entity.getCreatedAt() != null ? entity.getCreatedAt().atOffset(java.time.ZoneOffset.UTC) : null)")
    Favourite mapToFavourite(FavouriteEntity entity);
}
