package com.mathieuaime.happyhourfinder.trip.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.mathieuaime.happyhourfinder.bar.dto.BarDto;
import com.mathieuaime.happyhourfinder.bar.model.Bar;
import com.mathieuaime.happyhourfinder.trip.dto.TripDto;
import com.mathieuaime.happyhourfinder.trip.model.Trip;
import java.util.Collections;
import org.junit.Test;
import org.modelmapper.ModelMapper;

public class TripMapperTest {

  private static final TripMapper TRIP_MAPPER = new TripMapper(new ModelMapper());

  @Test
  public void convertTripEntityToTripDto_shouldSucceed() {
    Bar bar = Bar.builder().id(1L).name("Bar1").build();
    BarDto barDto = BarDto.builder().id(1L).name("Bar1").build();

    Trip trip = Trip.builder().bars(Collections.singletonList(bar)).build();

    TripDto tripDto = TRIP_MAPPER.convertToDto(trip);

    assertThat(tripDto.getBars().get(0)).isEqualTo(barDto);
  }
}