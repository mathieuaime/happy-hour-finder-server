package com.mathieuaime.happyhourfinder.api.bar;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

class JsonToPointDeserializer extends JsonDeserializer<GeoJsonPoint> {

  @Override
  public GeoJsonPoint deserialize(JsonParser parser, DeserializationContext context) {
    try {
      String text = parser.getText();
      if (text == null || text.length() <= 0) {
        return null;
      }

      String[] coordinates = text.replaceFirst("POINT ?\\(", "").replaceFirst("\\)", "").split(" ");
      double lat = Double.parseDouble(coordinates[0]);
      double lon = Double.parseDouble(coordinates[1]);

      return new GeoJsonPoint(lat, lon);
    } catch (Exception e) {
      return null;
    }
  }
}
