package com.mathieuaime.happyhourfinder.model.trip;

import com.mathieuaime.happyhourfinder.model.bar.Bar;
import java.util.Collections;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Trip {

  private static final Trip EMPTY = Trip.builder().bars(Collections.emptyList()).build();

  private final List<Bar> bars;

  public List<Bar> getBars() {
    return bars;
  }

  public static Trip empty() {
    return EMPTY;
  }
}
