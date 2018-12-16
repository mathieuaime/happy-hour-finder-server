package com.mathieuaime.happyhourfinder.bar.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

public class JsonToPointDeserializer extends JsonDeserializer<Point> {

  private static final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(),
      26910);

  @Override
  public Point deserialize(JsonParser parser, DeserializationContext context) {
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
