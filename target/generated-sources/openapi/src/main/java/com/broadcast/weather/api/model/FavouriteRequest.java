package com.broadcast.weather.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * FavouriteRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.7.0")
public class FavouriteRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String locationName;

  private String country;

  private Double latitude;

  private Double longitude;

  public FavouriteRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public FavouriteRequest(String locationName) {
    this.locationName = locationName;
  }

  public FavouriteRequest locationName(String locationName) {
    this.locationName = locationName;
    return this;
  }

  /**
   * Name of the location to save
   * @return locationName
   */
  @NotNull @Size(min = 1) 
  @Schema(name = "locationName", example = "London", description = "Name of the location to save", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("locationName")
  public String getLocationName() {
    return locationName;
  }

  public void setLocationName(String locationName) {
    this.locationName = locationName;
  }

  public FavouriteRequest country(String country) {
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

  public FavouriteRequest latitude(Double latitude) {
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

  public FavouriteRequest longitude(Double longitude) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FavouriteRequest favouriteRequest = (FavouriteRequest) o;
    return Objects.equals(this.locationName, favouriteRequest.locationName) &&
        Objects.equals(this.country, favouriteRequest.country) &&
        Objects.equals(this.latitude, favouriteRequest.latitude) &&
        Objects.equals(this.longitude, favouriteRequest.longitude);
  }

  @Override
  public int hashCode() {
    return Objects.hash(locationName, country, latitude, longitude);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FavouriteRequest {\n");
    sb.append("    locationName: ").append(toIndentedString(locationName)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
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

