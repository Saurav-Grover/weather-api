package com.broadcast.weather.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Favourite
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.7.0")
public class Favourite implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String locationName;

  private String country;

  private Double latitude;

  private Double longitude;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime createdAt;

  public Favourite() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Favourite(Long id, String locationName) {
    this.id = id;
    this.locationName = locationName;
  }

  public Favourite id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Unique favourite ID
   * @return id
   */
  @NotNull 
  @Schema(name = "id", example = "1", description = "Unique favourite ID", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Favourite locationName(String locationName) {
    this.locationName = locationName;
    return this;
  }

  /**
   * Name of the saved location
   * @return locationName
   */
  @NotNull 
  @Schema(name = "locationName", example = "London", description = "Name of the saved location", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("locationName")
  public String getLocationName() {
    return locationName;
  }

  public void setLocationName(String locationName) {
    this.locationName = locationName;
  }

  public Favourite country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Country of the location
   * @return country
   */
  
  @Schema(name = "country", example = "United Kingdom", description = "Country of the location", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("country")
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Favourite latitude(Double latitude) {
    this.latitude = latitude;
    return this;
  }

  /**
   * Latitude
   * @return latitude
   */
  
  @Schema(name = "latitude", example = "51.52", description = "Latitude", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("latitude")
  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Favourite longitude(Double longitude) {
    this.longitude = longitude;
    return this;
  }

  /**
   * Longitude
   * @return longitude
   */
  
  @Schema(name = "longitude", example = "-0.11", description = "Longitude", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("longitude")
  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Favourite createdAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Timestamp when favourite was saved
   * @return createdAt
   */
  @Valid 
  @Schema(name = "createdAt", description = "Timestamp when favourite was saved", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("createdAt")
  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Favourite favourite = (Favourite) o;
    return Objects.equals(this.id, favourite.id) &&
        Objects.equals(this.locationName, favourite.locationName) &&
        Objects.equals(this.country, favourite.country) &&
        Objects.equals(this.latitude, favourite.latitude) &&
        Objects.equals(this.longitude, favourite.longitude) &&
        Objects.equals(this.createdAt, favourite.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, locationName, country, latitude, longitude, createdAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Favourite {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    locationName: ").append(toIndentedString(locationName)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

