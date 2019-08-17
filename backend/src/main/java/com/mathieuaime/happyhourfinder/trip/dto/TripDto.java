package com.mathieuaime.happyhourfinder.trip.dto;

import com.mathieuaime.happyhourfinder.bar.dto.BarDto;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TripDto {

  private final List<BarDto> bars;

  public List<BarDto> getBars() {
    return bars;
  }
}
