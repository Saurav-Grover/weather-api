package com.broadcast.weather.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.broadcast.weather.api.entity.FavouriteEntity;
import com.broadcast.weather.api.exception.LocationNotFoundException;
import com.broadcast.weather.api.mapper.FavouriteMapper;
import com.broadcast.weather.api.model.Favourite;
import com.broadcast.weather.api.model.FavouriteRequest;
import com.broadcast.weather.api.repository.FavouriteRepository;
import com.broadcast.weather.api.service.impl.FavouriteServiceImpl;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FavouriteServiceTest {

    @Mock
    private FavouriteRepository favouriteRepository;

    @Mock
    private FavouriteMapper mapper;

    @InjectMocks
    private FavouriteServiceImpl favouriteService;

    @Test
    void shouldSaveFavourite() {
        FavouriteRequest request = new FavouriteRequest();
        request.setLocationName("London");
        request.setCountry("United Kingdom");
        request.setLatitude(51.52);
        request.setLongitude(-0.11);

        FavouriteEntity entity = FavouriteEntity.builder()
            .locationName("London").country("United Kingdom")
            .latitude(51.52).longitude(-0.11).createdAt(LocalDateTime.now()).build();
        FavouriteEntity savedEntity = FavouriteEntity.builder()
            .id(1L).locationName("London").country("United Kingdom")
            .latitude(51.52).longitude(-0.11).createdAt(LocalDateTime.now()).build();
        Favourite expected = new Favourite(1L, "London");
        expected.setCountry("United Kingdom");

        when(mapper.mapToFavouriteEntity(request)).thenReturn(entity);
        when(favouriteRepository.save(entity)).thenReturn(savedEntity);
        when(mapper.mapToFavourite(savedEntity)).thenReturn(expected);

        Favourite saved = favouriteService.createFavourite(request);

        assertThat(saved.getId()).isEqualTo(1L);
        assertThat(saved.getLocationName()).isEqualTo("London");
        assertThat(saved.getCountry()).isEqualTo("United Kingdom");
    }

    @Test
    void shouldListFavourites() {
        FavouriteEntity entity1 = FavouriteEntity.builder()
            .id(1L).locationName("London").country("United Kingdom")
            .latitude(51.52).longitude(-0.11).createdAt(LocalDateTime.now()).build();
        FavouriteEntity entity2 = FavouriteEntity.builder()
            .id(2L).locationName("Paris").country("France")
            .latitude(48.85).longitude(2.35).createdAt(LocalDateTime.now()).build();
        Favourite fav1 = new Favourite(1L, "London");
        Favourite fav2 = new Favourite(2L, "Paris");

        when(favouriteRepository.findAll()).thenReturn(List.of(entity1, entity2));
        when(mapper.mapToFavourite(entity1)).thenReturn(fav1);
        when(mapper.mapToFavourite(entity2)).thenReturn(fav2);

        List<Favourite> result = favouriteService.getAllFavourites();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getLocationName()).isEqualTo("London");
        assertThat(result.get(1).getLocationName()).isEqualTo("Paris");
    }

    @Test
    void shouldDeleteFavourite() {
        when(favouriteRepository.existsById(1L)).thenReturn(true);

        favouriteService.deleteFavourite(1L);

        verify(favouriteRepository).deleteById(1L);
    }

    @Test
    void shouldThrowWhenDeletingNonExistentFavourite() {
        when(favouriteRepository.existsById(99L)).thenReturn(false);

        assertThatThrownBy(() -> favouriteService.deleteFavourite(99L))
            .isInstanceOf(LocationNotFoundException.class)
            .hasMessageContaining("99");
    }
}
