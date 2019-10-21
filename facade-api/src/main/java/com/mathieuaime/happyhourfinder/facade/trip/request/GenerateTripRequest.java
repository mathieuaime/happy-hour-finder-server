package com.mathieuaime.happyhourfinder.facade.trip.request;

import com.google.common.base.MoreObjects;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * The Generate trip request.
 */
public class GenerateTripRequest {

  private int count;

  private List<String> mandatoryBars;

  /**
   * By count generate trip request.
   *
   * @param count the count
   * @return the generate trip request
   */
  public static GenerateTripRequest byCount(int count) {
    return byCountAndMandatoryBars(count, Collections.emptyList());
  }

  /**
   * By count and mandatory bars generate trip request.
   *
   * @param count the count
   * @param mandatoryBars the mandatory bars
   * @return the generate trip request
   */
  public static GenerateTripRequest byCountAndMandatoryBars(int count, List<String> mandatoryBars) {
    return new GenerateTripRequest(count, mandatoryBars);
  }

  private GenerateTripRequest(int count, List<String> mandatoryBars) {
    this.count = count;
    this.mandatoryBars = mandatoryBars;
  }

  public int getCount() {
    return count;
  }

  public List<String> getMandatoryBars() {
    return mandatoryBars;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GenerateTripRequest that = (GenerateTripRequest) o;
    return count == that.count &&
        Objects.equals(mandatoryBars, that.mandatoryBars);
  }

  @Override
  public int hashCode() {
    return Objects.hash(count, mandatoryBars);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("count", count)
        .add("mandatoryBars", mandatoryBars)
        .toString();
  }
}
