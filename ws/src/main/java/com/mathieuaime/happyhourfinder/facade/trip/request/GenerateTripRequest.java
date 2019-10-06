package com.mathieuaime.happyhourfinder.facade.trip.request;

import java.util.Collections;
import java.util.List;

/**
 * The Generate trip request.
 */
public class GenerateTripRequest {

  private int count;

  private List<Long> mandatoryBars;

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
  public static GenerateTripRequest byCountAndMandatoryBars(int count, List<Long> mandatoryBars) {
    return new GenerateTripRequest(count, mandatoryBars);
  }

  private GenerateTripRequest(int count, List<Long> mandatoryBars) {
    this.count = count;
    this.mandatoryBars = mandatoryBars;
  }

  public int getCount() {
    return count;
  }

  public List<Long> getMandatoryBars() {
    return mandatoryBars;
  }
}
