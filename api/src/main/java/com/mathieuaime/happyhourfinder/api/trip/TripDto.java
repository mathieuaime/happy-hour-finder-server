package com.mathieuaime.happyhourfinder.api.trip;

import com.google.common.base.MoreObjects;
import com.mathieuaime.happyhourfinder.api.bar.BarDto;
import java.util.List;
import java.util.Objects;

public class TripDto {

  private List<BarDto> bars;

  public List<BarDto> getBars() {
    return bars;
  }

  public void setBars(List<BarDto> bars) {
    this.bars = bars;
  }

  public TripDto bars(List<BarDto> bars) {
    this.bars = bars;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TripDto tripDto = (TripDto) o;
    return Objects.equals(bars, tripDto.bars);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bars);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("bars", bars)
        .toString();
  }
}
