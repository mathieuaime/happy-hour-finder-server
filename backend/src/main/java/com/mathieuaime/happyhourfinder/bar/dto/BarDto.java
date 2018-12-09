package com.mathieuaime.happyhourfinder.bar.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mathieuaime.happyhourfinder.bar.mapper.JsonToPointDeserializer;
import com.mathieuaime.happyhourfinder.bar.mapper.PointToJsonSerializer;
import com.vividsolutions.jts.geom.Point;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class BarDto implements Serializable {

  private Long id;

  @NonNull
  private String name;

  @JsonSerialize(using = PointToJsonSerializer.class)
  @JsonDeserialize(using = JsonToPointDeserializer.class)
  private Point coordinates;
}
