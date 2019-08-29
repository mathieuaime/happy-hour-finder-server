package com.mathieuaime.happyhourfinder.mapper.trip;

import com.mathieuaime.happyhourfinder.api.bar.BarDto;
import com.mathieuaime.happyhourfinder.api.trip.TripDto;
import com.mathieuaime.happyhourfinder.model.trip.Trip;
import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TripMapper {

  private final ModelMapper modelMapper;

  @Autowired
  public TripMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public TripDto convertToDto(Trip trip) {
    Type listType = new TypeToken<List<BarDto>>() {}.getType();
    return TripDto.builder().bars(modelMapper.map(trip.getBars(), listType)).build();
  }
}
