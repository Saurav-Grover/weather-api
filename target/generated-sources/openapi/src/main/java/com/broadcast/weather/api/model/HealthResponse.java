package com.broadcast.weather.api.model;

import java.net.URI;
import java.util.Objects;
import com.broadcast.weather.api.model.UpstreamApiHealth;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
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
 * HealthResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.7.0")
public class HealthResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Service status
   */
  public enum StatusEnum {
    UP("UP"),
    
    DEGRADED("DEGRADED"),
    
    DOWN("DOWN");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private StatusEnum status;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime timestamp;

  private UpstreamApiHealth upstreamApi;

  public HealthResponse() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public HealthResponse(StatusEnum status, OffsetDateTime timestamp) {
    this.status = status;
    this.timestamp = timestamp;
  }

  public HealthResponse status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Service status
   * @return status
   */
  @NotNull 
  @Schema(name = "status", example = "UP", description = "Service status", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("status")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public HealthResponse timestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Current server timestamp
   * @return timestamp
   */
  @NotNull @Valid 
  @Schema(name = "timestamp", description = "Current server timestamp", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("timestamp")
  public OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public HealthResponse upstreamApi(UpstreamApiHealth upstreamApi) {
    this.upstreamApi = upstreamApi;
    return this;
  }

  /**
   * Get upstreamApi
   * @return upstreamApi
   */
  @Valid 
  @Schema(name = "upstreamApi", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("upstreamApi")
  public UpstreamApiHealth getUpstreamApi() {
    return upstreamApi;
  }

  public void setUpstreamApi(UpstreamApiHealth upstreamApi) {
    this.upstreamApi = upstreamApi;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HealthResponse healthResponse = (HealthResponse) o;
    return Objects.equals(this.status, healthResponse.status) &&
        Objects.equals(this.timestamp, healthResponse.timestamp) &&
        Objects.equals(this.upstreamApi, healthResponse.upstreamApi);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, timestamp, upstreamApi);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HealthResponse {\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    upstreamApi: ").append(toIndentedString(upstreamApi)).append("\n");
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

