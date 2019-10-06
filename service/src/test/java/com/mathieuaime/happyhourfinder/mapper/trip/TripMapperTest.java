package com.mathieuaime.happyhourfinder.mapper.trip;

import static org.assertj.core.api.Assertions.assertThat;

import com.mathieuaime.happyhourfinder.api.bar.BarDto;
import com.mathieuaime.happyhourfinder.api.trip.TripDto;
import com.mathieuaime.happyhourfinder.model.bar.Bar;
import com.mathieuaime.happyhourfinder.model.trip.Trip;
import java.util.Collections;
import org.junit.Test;
import org.modelmapper.ModelMapper;

public class TripMapperTest {

  private static final TripMapper TRIP_MAPPER = new TripMapper(new ModelMapper());

  @Test
  public void convertTripEntityToTripDto_shouldSucceed() {
    Bar bar = new Bar().id(1L).name("Bar1");
    BarDto barDto = new BarDto().id(1L).name("Bar1");

    Trip trip = Trip.create(Collections.singletonList(bar));

    TripDto tripDto = TRIP_MAPPER.convertToDto(trip);

    assertThat(tripDto.getBars().get(0)).isEqualTo(barDto);
  }
}