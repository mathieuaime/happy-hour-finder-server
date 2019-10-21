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
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public class BarMapperTest {

  private static final BarMapper BAR_MAPPER = new BarMapper(new MapperConfig().modelMapper());

  @Test
  public void convertBarEntityToBarDto_shouldSucceed() {
    GeoJsonPoint point = new GeoJsonPoint(1, 2);
    HappyHour happyHour = new HappyHour().begin(LocalTime.of(12, 0)).duration(Duration.ofHours(1L));
    Bar bar = new Bar().uuid("uuid-1").name("Bar1").coordinates(point).happyHour(happyHour);

    BarDto barDto = BAR_MAPPER.convertToDto(bar);

    assertThat(barDto.getUuid()).isEqualTo(bar.getUuid());
    assertThat(barDto.getName()).isEqualTo(bar.getName());
    assertThat(barDto.getCoordinates()).isEqualTo(bar.getCoordinates());
    assertThat(barDto.getHappyHour().getBegin()).isEqualTo("12:00");
    assertThat(barDto.getHappyHour().getDuration()).isEqualTo("PT1H");
  }

  @Test
  public void convertBarDtoToBarEntity_shouldSucceed() {
    GeoJsonPoint point = new GeoJsonPoint(1.1, 2.1);
    HappyHourDto happyHourDto = new HappyHourDto().begin("12:00").duration("PT1H");
    BarDto barDto =
        new BarDto().uuid("uuid-1").name("Bar1").coordinates(point).happyHour(happyHourDto);

    Bar bar = BAR_MAPPER.convertToEntity(barDto);

    assertThat(bar.getUuid()).isEqualTo(barDto.getUuid());
    assertThat(bar.getName()).isEqualTo(barDto.getName());
    assertThat(bar.getCoordinates()).isEqualTo(barDto.getCoordinates());
    assertThat(bar.getHappyHour().getBegin()).isEqualTo(LocalTime.of(12, 0));
    assertThat(bar.getHappyHour().getDuration()).isEqualTo(Duration.ofHours(1L));
  }
}