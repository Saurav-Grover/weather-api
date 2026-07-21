package com.broadcast.weather.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * DailyForecast
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.7.0")
public class DailyForecast implements Serializable {

  private static final long serialVersionUID = 1L;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate date;

  private Double maxTempCelsius;

  private Double minTempCelsius;

  private String condition;

  private String conditionIcon;

  private Integer chanceOfRain;

  private Double maxWindSpeedKph;

  public DailyForecast() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public DailyForecast(LocalDate date, Double maxTempCelsius, Double minTempCelsius, String condition, Integer chanceOfRain) {
    this.date = date;
    this.maxTempCelsius = maxTempCelsius;
    this.minTempCelsius = minTempCelsius;
    this.condition = condition;
    this.chanceOfRain = chanceOfRain;
  }

  public DailyForecast date(LocalDate date) {
    this.date = date;
    return this;
  }

  /**
   * Forecast date
   * @return date
   */
  @NotNull @Valid 
  @Schema(name = "date", example = "Sat Jul 18 01:00:00 BST 2026", description = "Forecast date", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("date")
  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public DailyForecast maxTempCelsius(Double maxTempCelsius) {
    this.maxTempCelsius = maxTempCelsius;
    return this;
  }

  /**
   * Maximum temperature in Celsius
   * @return maxTempCelsius
   */
  @NotNull 
  @Schema(name = "maxTempCelsius", example = "18.0", description = "Maximum temperature in Celsius", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("maxTempCelsius")
  public Double getMaxTempCelsius() {
    return maxTempCelsius;
  }

  public void setMaxTempCelsius(Double maxTempCelsius) {
    this.maxTempCelsius = maxTempCelsius;
  }

  public DailyForecast minTempCelsius(Double minTempCelsius) {
    this.minTempCelsius = minTempCelsius;
    return this;
  }

  /**
   * Minimum temperature in Celsius
   * @return minTempCelsius
   */
  @NotNull 
  @Schema(name = "minTempCelsius", example = "10.0", description = "Minimum temperature in Celsius", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("minTempCelsius")
  public Double getMinTempCelsius() {
    return minTempCelsius;
  }

  public void setMinTempCelsius(Double minTempCelsius) {
    this.minTempCelsius = minTempCelsius;
  }

  public DailyForecast condition(String condition) {
    this.condition = condition;
    return this;
  }

  /**
   * Weather condition summary
   * @return condition
   */
  @NotNull 
  @Schema(name = "condition", example = "Sunny", description = "Weather condition summary", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("condition")
  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  public DailyForecast conditionIcon(String conditionIcon) {
    this.conditionIcon = conditionIcon;
    return this;
  }

  /**
   * URL to weather condition icon
   * @return conditionIcon
   */
  
  @Schema(name = "conditionIcon", example = "//cdn.weatherapi.com/weather/64x64/day/113.png", description = "URL to weather condition icon", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("conditionIcon")
  public String getConditionIcon() {
    return conditionIcon;
  }

  public void setConditionIcon(String conditionIcon) {
    this.conditionIcon = conditionIcon;
  }

  public DailyForecast chanceOfRain(Integer chanceOfRain) {
    this.chanceOfRain = chanceOfRain;
    return this;
  }

  /**
   * Precipitation probability percentage
   * @return chanceOfRain
   */
  @NotNull 
  @Schema(name = "chanceOfRain", example = "10", description = "Precipitation probability percentage", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("chanceOfRain")
  public Integer getChanceOfRain() {
    return chanceOfRain;
  }

  public void setChanceOfRain(Integer chanceOfRain) {
    this.chanceOfRain = chanceOfRain;
  }

  public DailyForecast maxWindSpeedKph(Double maxWindSpeedKph) {
    this.maxWindSpeedKph = maxWindSpeedKph;
    return this;
  }

  /**
   * Maximum wind speed in km/h
   * @return maxWindSpeedKph
   */
  
  @Schema(name = "maxWindSpeedKph", example = "20.5", description = "Maximum wind speed in km/h", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("maxWindSpeedKph")
  public Double getMaxWindSpeedKph() {
    return maxWindSpeedKph;
  }

  public void setMaxWindSpeedKph(Double maxWindSpeedKph) {
    this.maxWindSpeedKph = maxWindSpeedKph;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DailyForecast dailyForecast = (DailyForecast) o;
    return Objects.equals(this.date, dailyForecast.date) &&
        Objects.equals(this.maxTempCelsius, dailyForecast.maxTempCelsius) &&
        Objects.equals(this.minTempCelsius, dailyForecast.minTempCelsius) &&
        Objects.equals(this.condition, dailyForecast.condition) &&
        Objects.equals(this.conditionIcon, dailyForecast.conditionIcon) &&
        Objects.equals(this.chanceOfRain, dailyForecast.chanceOfRain) &&
        Objects.equals(this.maxWindSpeedKph, dailyForecast.maxWindSpeedKph);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, maxTempCelsius, minTempCelsius, condition, conditionIcon, chanceOfRain, maxWindSpeedKph);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DailyForecast {\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    maxTempCelsius: ").append(toIndentedString(maxTempCelsius)).append("\n");
    sb.append("    minTempCelsius: ").append(toIndentedString(minTempCelsius)).append("\n");
    sb.append("    condition: ").append(toIndentedString(condition)).append("\n");
    sb.append("    conditionIcon: ").append(toIndentedString(conditionIcon)).append("\n");
    sb.append("    chanceOfRain: ").append(toIndentedString(chanceOfRain)).append("\n");
    sb.append("    maxWindSpeedKph: ").append(toIndentedString(maxWindSpeedKph)).append("\n");
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

