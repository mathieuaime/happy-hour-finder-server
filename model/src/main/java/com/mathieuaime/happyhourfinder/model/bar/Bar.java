package com.mathieuaime.happyhourfinder.model.bar;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.util.Objects;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bars")
public class Bar {

  @Id
  private String uuid = UUID.randomUUID().toString();

  private String name;

  private GeoJsonPoint coordinates;

  private HappyHour happyHour;

  public String getUuid() {
    return uuid;
  }

  public Bar uuid(String uuid) {
    this.uuid = Preconditions.checkNotNull(uuid);
    return this;
  }

  public String getName() {
    return name;
  }

  public Bar name(String name) {
    this.name = Preconditions.checkNotNull(name);
    return this;
  }

  public GeoJsonPoint getCoordinates() {
    return coordinates;
  }

  public Bar coordinates(GeoJsonPoint coordinates) {
    this.coordinates = coordinates;
    return this;
  }

  public HappyHour getHappyHour() {
    return happyHour;
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
    return Objects.equals(uuid, bar.uuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid);
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
