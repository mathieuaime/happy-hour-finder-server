package com.mathieuaime.happyhourfinder.bar.mapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vividsolutions.jts.geom.Point;
import java.io.IOException;

public class PointToJsonSerializer extends JsonSerializer<Point> {

  @Override
  public void serialize(Point value, JsonGenerator generator, SerializerProvider serializers)
      throws IOException {
    String jsonValue = "null";
    if (value != null) {
      double lat = value.getX();
      double lon = value.getY();
      jsonValue = String.format("POINT (%s %s)", lat, lon);
    }
    generator.writeString(jsonValue);
  }
}
