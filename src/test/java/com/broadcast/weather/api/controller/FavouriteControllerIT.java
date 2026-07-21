package com.broadcast.weather.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.broadcast.weather.api.entity.FavouriteEntity;
import com.broadcast.weather.api.model.FavouriteRequest;
import com.broadcast.weather.api.repository.FavouriteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class FavouriteControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FavouriteRepository favouriteRepository;

    @BeforeEach
    void setUp() {
        favouriteRepository.deleteAll();
    }

    @Test
    void shouldCreateFavouriteAndReturn201() throws Exception {
        FavouriteRequest request = new FavouriteRequest();
        request.setLocationName("London");
        request.setCountry("United Kingdom");
        request.setLatitude(51.52);
        request.setLongitude(-0.11);

        mockMvc.perform(post("/api/v1/favourites")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.locationName").value("London"))
            .andExpect(jsonPath("$.country").value("United Kingdom"))
            .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void shouldReturnListOfFavourites() throws Exception {
        favouriteRepository.save(FavouriteEntity.builder()
            .locationName("London").country("United Kingdom")
            .latitude(51.52).longitude(-0.11).createdAt(LocalDateTime.now()).build());
        favouriteRepository.save(FavouriteEntity.builder()
            .locationName("Paris").country("France")
            .latitude(48.85).longitude(2.35).createdAt(LocalDateTime.now()).build());

        mockMvc.perform(get("/api/v1/favourites"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.favourites.length()").value(2));
    }

    @Test
    void shouldDeleteFavouriteAndReturn204() throws Exception {
        FavouriteEntity saved = favouriteRepository.save(FavouriteEntity.builder()
            .locationName("London").country("United Kingdom")
            .latitude(51.52).longitude(-0.11).createdAt(LocalDateTime.now()).build());

        mockMvc.perform(delete("/api/v1/favourites/" + saved.getId()))
            .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturn404WhenDeletingNonExistentFavourite() throws Exception {
        mockMvc.perform(delete("/api/v1/favourites/999"))
            .andExpect(status().isNotFound());
    }
}
