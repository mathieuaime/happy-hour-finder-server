package com.mathieuaime.happyhourfinder.model.bar;

import com.google.common.base.MoreObjects;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

public class HappyHour {

  private LocalTime begin;

  private Duration duration;

  public LocalTime getBegin() {
    return begin;
  }

  public void setBegin(LocalTime begin) {
    this.begin = begin;
  }

  public HappyHour begin(LocalTime begin) {
    this.begin = begin;
    return this;
  }

  public Duration getDuration() {
    return duration;
  }

  public void setDuration(Duration duration) {
    this.duration = duration;
  }

  public HappyHour duration(Duration duration) {
    this.duration = duration;
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
    HappyHour happyHour = (HappyHour) o;
    return Objects.equals(begin, happyHour.begin) &&
        Objects.equals(duration, happyHour.duration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(begin, duration);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("begin", begin)
        .add("duration", duration)
        .toString();
  }
}
