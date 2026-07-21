package com.broadcast.weather.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "favourites")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location_name", nullable = false)
    private String locationName;

    private String country;

    private Double latitude;

    private Double longitude;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
