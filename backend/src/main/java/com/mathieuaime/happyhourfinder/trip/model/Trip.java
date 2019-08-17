package com.mathieuaime.happyhourfinder.trip.model;

import com.mathieuaime.happyhourfinder.bar.model.Bar;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Trip {

  private final List<Bar> bars;

  public List<Bar> getBars() {
    return bars;
  }
}
