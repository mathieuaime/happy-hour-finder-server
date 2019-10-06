package com.mathieuaime.happyhourfinder.api.bar;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

class PointToJsonSerializer extends JsonSerializer<GeoJsonPoint> {

  @Override
  public void serialize(GeoJsonPoint value, JsonGenerator generator, SerializerProvider serializers)
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
