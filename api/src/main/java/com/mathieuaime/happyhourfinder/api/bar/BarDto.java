package com.mathieuaime.happyhourfinder.api.bar;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.MoreObjects;
import java.util.Objects;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public class BarDto {

  private Long id;

  private String name;

  @JsonSerialize(using = PointToJsonSerializer.class)
  @JsonDeserialize(using = JsonToPointDeserializer.class)
  private GeoJsonPoint coordinates;

  private HappyHourDto happyHour;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BarDto id(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BarDto name(String name) {
    this.name = name;
    return this;
  }

  public GeoJsonPoint getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(GeoJsonPoint coordinates) {
    this.coordinates = coordinates;
  }

  public BarDto coordinates(GeoJsonPoint coordinates) {
    this.coordinates = coordinates;
    return this;
  }

  public HappyHourDto getHappyHour() {
    return happyHour;
  }

  public void setHappyHour(HappyHourDto happyHour) {
    this.happyHour = happyHour;
  }

  public BarDto happyHour(HappyHourDto happyHour) {
    this.happyHour = happyHour;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BarDto barDto = (BarDto) o;
    return Objects.equals(id, barDto.id) &&
        Objects.equals(name, barDto.name) &&
        Objects.equals(coordinates, barDto.coordinates) &&
        Objects.equals(happyHour, barDto.happyHour);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, coordinates, happyHour);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("name", name)
        .add("coordinates", coordinates)
        .add("happyHour", happyHour)
        .toString();
  }
}
