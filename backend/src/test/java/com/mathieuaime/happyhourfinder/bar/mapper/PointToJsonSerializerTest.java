package com.mathieuaime.happyhourfinder.bar.mapper;

import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.core.JsonGenerator;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PointToJsonSerializerTest {

  @Mock
  private JsonGenerator generator;

  @InjectMocks
  private PointToJsonSerializer serializer;

  private static final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(),
      26910);

  @Before
  public void setUp() throws Exception {
    serializer = new PointToJsonSerializer();
  }

  @Test
  public void serialize() throws Exception {
    Point point = geometryFactory.createPoint(new Coordinate(1, 2));

    serializer.serialize(point, generator, null);

    verify(generator).writeString("POINT (1.0 2.0)");
  }

  @Test
  public void serializeNullValue() throws Exception {
    serializer.serialize(null, generator, null);

    verify(generator).writeString("null");
  }

}