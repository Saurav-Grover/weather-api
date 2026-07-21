package com.broadcast.weather.api.model;

import java.net.URI;
import java.util.Objects;
import com.broadcast.weather.api.model.Location;
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
 * LocationSearchResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.7.0")
public class LocationSearchResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  @Valid
  private List<@Valid Location> locations = new ArrayList<>();

  public LocationSearchResponse locations(List<@Valid Location> locations) {
    this.locations = locations;
    return this;
  }

  public LocationSearchResponse addLocationsItem(Location locationsItem) {
    if (this.locations == null) {
      this.locations = new ArrayList<>();
    }
    this.locations.add(locationsItem);
    return this;
  }

  /**
   * Get locations
   * @return locations
   */
  @Valid 
  @Schema(name = "locations", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("locations")
  public List<@Valid Location> getLocations() {
    return locations;
  }

  public void setLocations(List<@Valid Location> locations) {
    this.locations = locations;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LocationSearchResponse locationSearchResponse = (LocationSearchResponse) o;
    return Objects.equals(this.locations, locationSearchResponse.locations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(locations);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LocationSearchResponse {\n");
    sb.append("    locations: ").append(toIndentedString(locations)).append("\n");
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

