package com.mathieuaime.happyhourfinder.api.bar;

import com.google.common.base.MoreObjects;
import java.util.Objects;

public class HappyHourDto {

  private String begin;
  private String duration;

  public String getBegin() {
    return begin;
  }

  public void setBegin(String begin) {
    this.begin = begin;
  }

  public HappyHourDto begin(String begin) {
    this.begin = begin;
    return this;
  }

  public String getDuration() {
    return duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

  public HappyHourDto duration(String duration) {
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
    HappyHourDto that = (HappyHourDto) o;
    return Objects.equals(begin, that.begin) &&
        Objects.equals(duration, that.duration);
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