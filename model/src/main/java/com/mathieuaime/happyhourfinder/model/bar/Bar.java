package com.mathieuaime.happyhourfinder.model.bar;

import com.google.common.base.MoreObjects;
import java.util.Objects;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bars")
public class Bar {

  private Long id;

  private String name;

  private GeoJsonPoint coordinates;

  private HappyHour happyHour;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Bar id(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Bar name(String name) {
    this.name = name;
    return this;
  }

  public GeoJsonPoint getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(GeoJsonPoint coordinates) {
    this.coordinates = coordinates;
  }

  public Bar coordinates(GeoJsonPoint coordinates) {
    this.coordinates = coordinates;
    return this;
  }

  public HappyHour getHappyHour() {
    return happyHour;
  }

  public void setHappyHour(HappyHour happyHour) {
    this.happyHour = happyHour;
  }

  public Bar happyHour(HappyHour happyHour) {
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
    Bar bar = (Bar) o;
    return Objects.equals(id, bar.id) &&
        Objects.equals(name, bar.name) &&
        Objects.equals(coordinates, bar.coordinates) &&
        Objects.equals(happyHour, bar.happyHour);
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
