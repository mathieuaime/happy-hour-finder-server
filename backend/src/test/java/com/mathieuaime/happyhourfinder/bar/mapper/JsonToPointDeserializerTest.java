package com.mathieuaime.happyhourfinder.bar.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.fasterxml.jackson.core.JsonParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.locationtech.jts.geom.Point;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JsonToPointDeserializerTest {

  @Mock
  private JsonParser parser;

  @InjectMocks
  private JsonToPointDeserializer deserializer;

  @Before
  public void setUp() {
    deserializer = new JsonToPointDeserializer();
  }

  @Test
  public void deserialize() throws Exception {
    Mockito.when(parser.getText()).thenReturn("POINT (-1.0 2.0)");

    Point point = deserializer.deserialize(parser, null);

    assertEquals(-1.0, point.getX(), 0.01);
    assertEquals(2.0, point.getY(), 0.01);
  }

  @Test
  public void deserializeWithBadFormat() throws Exception {
    Mockito.when(parser.getText()).thenReturn("POINT (A B)");

    Point point = deserializer.deserialize(parser, null);

    assertNull(point);
  }

  @Test
  public void deserializeWithNoData() throws Exception {
    Mockito.when(parser.getText()).thenReturn("");

    Point point = deserializer.deserialize(parser, null);

    assertNull(point);
  }

  @Test
  public void deserializeWithNullData() throws Exception {
    Mockito.when(parser.getText()).thenReturn(null);

    Point point = deserializer.deserialize(parser, null);

    assertNull(point);
  }

}