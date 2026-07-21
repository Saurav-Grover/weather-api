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
 * CurrentWeather
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.7.0")
public class CurrentWeather implements Serializable {

  private static final long serialVersionUID = 1L;

  private String location;

  private String country;

  private Double temperatureCelsius;

  private Double temperatureFahrenheit;

  private String condition;

  private String conditionIcon;

  private Double windSpeedKph;

  private Integer humidity;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime lastUpdated;

  public CurrentWeather() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CurrentWeather(String location, String country, Double temperatureCelsius, String condition, Double windSpeedKph, Integer humidity, OffsetDateTime lastUpdated) {
    this.location = location;
    this.country = country;
    this.temperatureCelsius = temperatureCelsius;
    this.condition = condition;
    this.windSpeedKph = windSpeedKph;
    this.humidity = humidity;
    this.lastUpdated = lastUpdated;
  }

  public CurrentWeather location(String location) {
    this.location = location;
    return this;
  }

  /**
   * Location name
   * @return location
   */
  @NotNull 
  @Schema(name = "location", example = "London", description = "Location name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("location")
  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public CurrentWeather country(String country) {
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

  public CurrentWeather temperatureCelsius(Double temperatureCelsius) {
    this.temperatureCelsius = temperatureCelsius;
    return this;
  }

  /**
   * Temperature in Celsius
   * @return temperatureCelsius
   */
  @NotNull 
  @Schema(name = "temperatureCelsius", example = "15.0", description = "Temperature in Celsius", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("temperatureCelsius")
  public Double getTemperatureCelsius() {
    return temperatureCelsius;
  }

  public void setTemperatureCelsius(Double temperatureCelsius) {
    this.temperatureCelsius = temperatureCelsius;
  }

  public CurrentWeather temperatureFahrenheit(Double temperatureFahrenheit) {
    this.temperatureFahrenheit = temperatureFahrenheit;
    return this;
  }

  /**
   * Temperature in Fahrenheit
   * @return temperatureFahrenheit
   */
  
  @Schema(name = "temperatureFahrenheit", example = "59.0", description = "Temperature in Fahrenheit", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("temperatureFahrenheit")
  public Double getTemperatureFahrenheit() {
    return temperatureFahrenheit;
  }

  public void setTemperatureFahrenheit(Double temperatureFahrenheit) {
    this.temperatureFahrenheit = temperatureFahrenheit;
  }

  public CurrentWeather condition(String condition) {
    this.condition = condition;
    return this;
  }

  /**
   * Weather condition text
   * @return condition
   */
  @NotNull 
  @Schema(name = "condition", example = "Partly cloudy", description = "Weather condition text", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("condition")
  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  public CurrentWeather conditionIcon(String conditionIcon) {
    this.conditionIcon = conditionIcon;
    return this;
  }

  /**
   * URL to weather condition icon
   * @return conditionIcon
   */
  
  @Schema(name = "conditionIcon", example = "//cdn.weatherapi.com/weather/64x64/day/116.png", description = "URL to weather condition icon", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("conditionIcon")
  public String getConditionIcon() {
    return conditionIcon;
  }

  public void setConditionIcon(String conditionIcon) {
    this.conditionIcon = conditionIcon;
  }

  public CurrentWeather windSpeedKph(Double windSpeedKph) {
    this.windSpeedKph = windSpeedKph;
    return this;
  }

  /**
   * Wind speed in km/h
   * @return windSpeedKph
   */
  @NotNull 
  @Schema(name = "windSpeedKph", example = "12.2", description = "Wind speed in km/h", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("windSpeedKph")
  public Double getWindSpeedKph() {
    return windSpeedKph;
  }

  public void setWindSpeedKph(Double windSpeedKph) {
    this.windSpeedKph = windSpeedKph;
  }

  public CurrentWeather humidity(Integer humidity) {
    this.humidity = humidity;
    return this;
  }

  /**
   * Humidity percentage
   * @return humidity
   */
  @NotNull 
  @Schema(name = "humidity", example = "72", description = "Humidity percentage", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("humidity")
  public Integer getHumidity() {
    return humidity;
  }

  public void setHumidity(Integer humidity) {
    this.humidity = humidity;
  }

  public CurrentWeather lastUpdated(OffsetDateTime lastUpdated) {
    this.lastUpdated = lastUpdated;
    return this;
  }

  /**
   * Timestamp of when data was last updated
   * @return lastUpdated
   */
  @NotNull @Valid 
  @Schema(name = "lastUpdated", description = "Timestamp of when data was last updated", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("lastUpdated")
  public OffsetDateTime getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(OffsetDateTime lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CurrentWeather currentWeather = (CurrentWeather) o;
    return Objects.equals(this.location, currentWeather.location) &&
        Objects.equals(this.country, currentWeather.country) &&
        Objects.equals(this.temperatureCelsius, currentWeather.temperatureCelsius) &&
        Objects.equals(this.temperatureFahrenheit, currentWeather.temperatureFahrenheit) &&
        Objects.equals(this.condition, currentWeather.condition) &&
        Objects.equals(this.conditionIcon, currentWeather.conditionIcon) &&
        Objects.equals(this.windSpeedKph, currentWeather.windSpeedKph) &&
        Objects.equals(this.humidity, currentWeather.humidity) &&
        Objects.equals(this.lastUpdated, currentWeather.lastUpdated);
  }

  @Override
  public int hashCode() {
    return Objects.hash(location, country, temperatureCelsius, temperatureFahrenheit, condition, conditionIcon, windSpeedKph, humidity, lastUpdated);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CurrentWeather {\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    temperatureCelsius: ").append(toIndentedString(temperatureCelsius)).append("\n");
    sb.append("    temperatureFahrenheit: ").append(toIndentedString(temperatureFahrenheit)).append("\n");
    sb.append("    condition: ").append(toIndentedString(condition)).append("\n");
    sb.append("    conditionIcon: ").append(toIndentedString(conditionIcon)).append("\n");
    sb.append("    windSpeedKph: ").append(toIndentedString(windSpeedKph)).append("\n");
    sb.append("    humidity: ").append(toIndentedString(humidity)).append("\n");
    sb.append("    lastUpdated: ").append(toIndentedString(lastUpdated)).append("\n");
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

