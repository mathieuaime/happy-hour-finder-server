package com.mathieuaime.happyhourfinder.bar.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;
import java.io.IOException;

public class JsonToPointDeserializer extends JsonDeserializer<Point> {

  private static final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(),
      26910);

  @Override
  public Point deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    try {
      String text = parser.getText();
      if (text == null || text.length() <= 0) {
        return null;
      }

      String[] coordinates = text.replaceFirst("POINT ?\\(", "").replaceFirst("\\)", "").split(" ");
      double lat = Double.parseDouble(coordinates[0]);
      double lon = Double.parseDouble(coordinates[1]);

      return geometryFactory.createPoint(new Coordinate(lat, lon));
    } catch (Exception e) {
      return null;
    }
  }
}
