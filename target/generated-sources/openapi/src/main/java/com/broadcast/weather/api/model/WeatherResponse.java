package com.broadcast.weather.api.model;

import java.net.URI;
import java.util.Objects;
import com.broadcast.weather.api.model.CurrentWeather;
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
 * WeatherResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.7.0")
public class WeatherResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private CurrentWeather data;

  private String source;

  public WeatherResponse data(CurrentWeather data) {
    this.data = data;
    return this;
  }

  /**
   * Get data
   * @return data
   */
  @Valid 
  @Schema(name = "data", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("data")
  public CurrentWeather getData() {
    return data;
  }

  public void setData(CurrentWeather data) {
    this.data = data;
  }

  public WeatherResponse source(String source) {
    this.source = source;
    return this;
  }

  /**
   * Data source identifier
   * @return source
   */
  
  @Schema(name = "source", example = "weatherapi.com", description = "Data source identifier", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("source")
  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WeatherResponse weatherResponse = (WeatherResponse) o;
    return Objects.equals(this.data, weatherResponse.data) &&
        Objects.equals(this.source, weatherResponse.source);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, source);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WeatherResponse {\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    source: ").append(toIndentedString(source)).append("\n");
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

