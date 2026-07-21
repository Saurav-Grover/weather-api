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
 * Location
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.7.0")
public class Location implements Serializable {

  private static final long serialVersionUID = 1L;

  private String name;

  private String region;

  private String country;

  private Double latitude;

  private Double longitude;

  public Location() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Location(String name, String country, Double latitude, Double longitude) {
    this.name = name;
    this.country = country;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public Location name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Location name
   * @return name
   */
  @NotNull 
  @Schema(name = "name", example = "London", description = "Location name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Location region(String region) {
    this.region = region;
    return this;
  }

  /**
   * Region or state
   * @return region
   */
  
  @Schema(name = "region", example = "City of London, Greater London", description = "Region or state", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("region")
  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public Location country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Country name
   * @return country
   */
  @NotNull 
  @Schema(name = "country", example = "United Kingdom", description = "Country name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("country")
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Location latitude(Double latitude) {
    this.latitude = latitude;
    return this;
  }

  /**
   * Latitude
   * @return latitude
   */
  @NotNull 
  @Schema(name = "latitude", example = "51.52", description = "Latitude", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("latitude")
  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Location longitude(Double longitude) {
    this.longitude = longitude;
    return this;
  }

  /**
   * Longitude
   * @return longitude
   */
  @NotNull 
  @Schema(name = "longitude", example = "-0.11", description = "Longitude", requiredMode = Schema.RequiredMode.REQUIRED)
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
    Location location = (Location) o;
    return Objects.equals(this.name, location.name) &&
        Objects.equals(this.region, location.region) &&
        Objects.equals(this.country, location.country) &&
        Objects.equals(this.latitude, location.latitude) &&
        Objects.equals(this.longitude, location.longitude);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, region, country, latitude, longitude);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Location {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    region: ").append(toIndentedString(region)).append("\n");
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

