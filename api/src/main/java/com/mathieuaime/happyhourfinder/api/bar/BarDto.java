package com.mathieuaime.happyhourfinder.api.bar;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.MoreObjects;
import java.util.Objects;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public class BarDto {

  private String uuid;

  private String name;

  @JsonSerialize(using = PointToJsonSerializer.class)
  @JsonDeserialize(using = JsonToPointDeserializer.class)
  private GeoJsonPoint coordinates;

  private HappyHourDto happyHour;

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public BarDto uuid(String uuid) {
    this.uuid = uuid;
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
    return Objects.equals(uuid, barDto.uuid) &&
        Objects.equals(name, barDto.name) &&
        Objects.equals(coordinates, barDto.coordinates) &&
        Objects.equals(happyHour, barDto.happyHour);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, name, coordinates, happyHour);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("uuid", uuid)
        .add("name", name)
        .add("coordinates", coordinates)
        .add("happyHour", happyHour)
        .toString();
  }
}
