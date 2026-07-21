package com.broadcast.weather.api.model;

import java.net.URI;
import java.util.Objects;
import com.broadcast.weather.api.model.DailyForecast;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ForecastData
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.7.0")
public class ForecastData implements Serializable {

  private static final long serialVersionUID = 1L;

  private String location;

  private String country;

  @Valid
  private List<@Valid DailyForecast> days = new ArrayList<>();

  public ForecastData() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ForecastData(String location, String country, List<@Valid DailyForecast> days) {
    this.location = location;
    this.country = country;
    this.days = days;
  }

  public ForecastData location(String location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
   */
  @NotNull 
  @Schema(name = "location", example = "London", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("location")
  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public ForecastData country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Get country
   * @return country
   */
  @NotNull 
  @Schema(name = "country", example = "United Kingdom", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("country")
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public ForecastData days(List<@Valid DailyForecast> days) {
    this.days = days;
    return this;
  }

  public ForecastData addDaysItem(DailyForecast daysItem) {
    if (this.days == null) {
      this.days = new ArrayList<>();
    }
    this.days.add(daysItem);
    return this;
  }

  /**
   * Get days
   * @return days
   */
  @NotNull @Valid 
  @Schema(name = "days", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("days")
  public List<@Valid DailyForecast> getDays() {
    return days;
  }

  public void setDays(List<@Valid DailyForecast> days) {
    this.days = days;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ForecastData forecastData = (ForecastData) o;
    return Objects.equals(this.location, forecastData.location) &&
        Objects.equals(this.country, forecastData.country) &&
        Objects.equals(this.days, forecastData.days);
  }

  @Override
  public int hashCode() {
    return Objects.hash(location, country, days);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ForecastData {\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    days: ").append(toIndentedString(days)).append("\n");
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

