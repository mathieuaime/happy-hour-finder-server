package com.mathieuaime.happyhourfinder.api.bar;

import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@RunWith(MockitoJUnitRunner.class)
public class PointToJsonSerializerTest {

  @Mock
  private JsonGenerator generator;

  @InjectMocks
  private PointToJsonSerializer serializer;

  @Before
  public void setUp() {
    serializer = new PointToJsonSerializer();
  }

  @Test
  public void serialize() throws Exception {
    GeoJsonPoint point = new GeoJsonPoint(-1, 2);

    serializer.serialize(point, generator, null);

    verify(generator).writeString("POINT (-1.0 2.0)");
  }

  @Test
  public void serializeNullValue() throws Exception {
    serializer.serialize(null, generator, null);

    verify(generator).writeString("null");
  }
}