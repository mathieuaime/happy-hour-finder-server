package com.mathieuaime.happyhourfinder.mapper.bar;

import static org.assertj.core.api.Assertions.assertThat;

import com.mathieuaime.happyhourfinder.api.bar.BarDto;
import com.mathieuaime.happyhourfinder.api.bar.HappyHourDto;
import com.mathieuaime.happyhourfinder.config.MapperConfig;
import com.mathieuaime.happyhourfinder.model.bar.Bar;
import com.mathieuaime.happyhourfinder.model.bar.HappyHour;
import java.time.Duration;
import java.time.LocalTime;
import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

public class BarMapperTest {

  private static final BarMapper BAR_MAPPER = new BarMapper(new MapperConfig().modelMapper());
  private static final GeometryFactory GEOMETRY_FACTORY =
      new GeometryFactory(new PrecisionModel(), 26910);

  @Test
  public void convertBarEntityToBarDto_shouldSucceed() {
    Point point = GEOMETRY_FACTORY.createPoint(new Coordinate(1.1, 2.1));
    HappyHour happyHour = HappyHour.create(LocalTime.of(12, 0), Duration.ofHours(1L));
    Bar bar = Bar.create(1L, "Bar1", point, happyHour);

    BarDto barDto = BAR_MAPPER.convertToDto(bar);

    assertThat(barDto.getId()).isEqualTo(bar.getId());
    assertThat(barDto.getName()).isEqualTo(bar.getName());
    assertThat(barDto.getCoordinates()).isEqualTo(bar.getCoordinates());
    assertThat(barDto.getHappyHour().getBegin()).isEqualTo("12:00");
    assertThat(barDto.getHappyHour().getDuration()).isEqualTo("PT1H");
  }

  @Test
  public void convertBarDtoToBarEntity_shouldSucceed() {
    Point point = GEOMETRY_FACTORY.createPoint(new Coordinate(1.1, 2.1));
    HappyHourDto happyHourDto = HappyHourDto.create("12:00", "PT1H");
    BarDto barDto = BarDto.create(1L, "Bar1", point, happyHourDto);

    Bar bar = BAR_MAPPER.convertToEntity(barDto);

    assertThat(bar.getId()).isEqualTo(barDto.getId());
    assertThat(bar.getName()).isEqualTo(barDto.getName());
    assertThat(bar.getCoordinates()).isEqualTo(barDto.getCoordinates());
    assertThat(bar.getHappyHour().getBegin()).isEqualTo(LocalTime.of(12, 0));
    assertThat(bar.getHappyHour().getDuration()).isEqualTo(Duration.ofHours(1L));
  }
}