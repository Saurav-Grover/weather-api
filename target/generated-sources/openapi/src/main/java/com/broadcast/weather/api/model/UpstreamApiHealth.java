package com.broadcast.weather.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * UpstreamApiHealth
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.7.0")
public class UpstreamApiHealth implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Current circuit breaker state
   */
  public enum CircuitBreakerStateEnum {
    CLOSED("CLOSED"),
    
    OPEN("OPEN"),
    
    HALF_OPEN("HALF_OPEN");

    private String value;

    CircuitBreakerStateEnum(String value) {
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
    public static CircuitBreakerStateEnum fromValue(String value) {
      for (CircuitBreakerStateEnum b : CircuitBreakerStateEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private CircuitBreakerStateEnum circuitBreakerState;

  private Float failureRate;

  public UpstreamApiHealth circuitBreakerState(CircuitBreakerStateEnum circuitBreakerState) {
    this.circuitBreakerState = circuitBreakerState;
    return this;
  }

  /**
   * Current circuit breaker state
   * @return circuitBreakerState
   */
  
  @Schema(name = "circuitBreakerState", example = "CLOSED", description = "Current circuit breaker state", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("circuitBreakerState")
  public CircuitBreakerStateEnum getCircuitBreakerState() {
    return circuitBreakerState;
  }

  public void setCircuitBreakerState(CircuitBreakerStateEnum circuitBreakerState) {
    this.circuitBreakerState = circuitBreakerState;
  }

  public UpstreamApiHealth failureRate(Float failureRate) {
    this.failureRate = failureRate;
    return this;
  }

  /**
   * Current failure rate percentage
   * @return failureRate
   */
  
  @Schema(name = "failureRate", example = "0.0", description = "Current failure rate percentage", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("failureRate")
  public Float getFailureRate() {
    return failureRate;
  }

  public void setFailureRate(Float failureRate) {
    this.failureRate = failureRate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpstreamApiHealth upstreamApiHealth = (UpstreamApiHealth) o;
    return Objects.equals(this.circuitBreakerState, upstreamApiHealth.circuitBreakerState) &&
        Objects.equals(this.failureRate, upstreamApiHealth.failureRate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(circuitBreakerState, failureRate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpstreamApiHealth {\n");
    sb.append("    circuitBreakerState: ").append(toIndentedString(circuitBreakerState)).append("\n");
    sb.append("    failureRate: ").append(toIndentedString(failureRate)).append("\n");
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

