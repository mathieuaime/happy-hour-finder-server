package com.mathieuaime.happyhourfinder.bar.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mathieuaime.happyhourfinder.bar.mapper.JsonToPointDeserializer;
import com.mathieuaime.happyhourfinder.bar.mapper.PointToJsonSerializer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "bars")
@Data
@Builder
public class Bar {

  @Id
  @GeneratedValue
  private Long id;

  @NonNull
  private String name;

  @Column(columnDefinition = "POINT")
  @JsonSerialize(using = PointToJsonSerializer.class)
  @JsonDeserialize(using = JsonToPointDeserializer.class)
  private Point coordinates;
}
