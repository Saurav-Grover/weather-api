package com.broadcast.weather.api.model;

import java.net.URI;
import java.util.Objects;
import com.broadcast.weather.api.model.Favourite;
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
 * FavouriteListResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.7.0")
public class FavouriteListResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  @Valid
  private List<@Valid Favourite> favourites = new ArrayList<>();

  public FavouriteListResponse favourites(List<@Valid Favourite> favourites) {
    this.favourites = favourites;
    return this;
  }

  public FavouriteListResponse addFavouritesItem(Favourite favouritesItem) {
    if (this.favourites == null) {
      this.favourites = new ArrayList<>();
    }
    this.favourites.add(favouritesItem);
    return this;
  }

  /**
   * Get favourites
   * @return favourites
   */
  @Valid 
  @Schema(name = "favourites", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("favourites")
  public List<@Valid Favourite> getFavourites() {
    return favourites;
  }

  public void setFavourites(List<@Valid Favourite> favourites) {
    this.favourites = favourites;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FavouriteListResponse favouriteListResponse = (FavouriteListResponse) o;
    return Objects.equals(this.favourites, favouriteListResponse.favourites);
  }

  @Override
  public int hashCode() {
    return Objects.hash(favourites);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FavouriteListResponse {\n");
    sb.append("    favourites: ").append(toIndentedString(favourites)).append("\n");
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

