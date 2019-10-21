package com.mathieuaime.happyhourfinder.model.trip;

import com.google.common.collect.ImmutableList;
import com.mathieuaime.happyhourfinder.model.bar.Bar;
import java.util.Collections;
import java.util.List;

public class Trip {

  private static final Trip EMPTY = Trip.create(Collections.emptyList());

  private final List<Bar> bars;

  private Trip(List<Bar> bars) {
    this.bars = bars;
  }

  public static Trip create(Iterable<Bar> bars) {
    return new Trip(ImmutableList.copyOf(bars));
  }

  public static Trip empty() {
    return EMPTY;
  }

  public List<Bar> getBars() {
    return bars;
  }
}
