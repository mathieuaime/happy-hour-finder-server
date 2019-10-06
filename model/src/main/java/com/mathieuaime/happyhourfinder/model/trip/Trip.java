package com.mathieuaime.happyhourfinder.model.trip;

import com.google.common.collect.ImmutableList;
import com.mathieuaime.happyhourfinder.model.bar.Bar;
import java.util.Collections;
import java.util.List;

public class Trip {

  private static final Trip EMPTY = Trip.create(Collections.emptyList());

  private final List<Bar> bars;

  public static Trip create(Iterable<Bar> bars) {
    return new Trip(ImmutableList.copyOf(bars));
  }

  private Trip(List<Bar> bars) {
    this.bars = bars;
  }

  public List<Bar> getBars() {
    return bars;
  }

  public static Trip empty() {
    return EMPTY;
  }
}
