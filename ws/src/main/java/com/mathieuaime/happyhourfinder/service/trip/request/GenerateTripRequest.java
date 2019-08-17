package com.mathieuaime.happyhourfinder.service.trip.request;

import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The Generate trip request.
 */
@Data
@AllArgsConstructor
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
}
