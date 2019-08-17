package com.mathieuaime.happyhourfinder.api.trip;

import com.mathieuaime.happyhourfinder.api.bar.BarDto;
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
