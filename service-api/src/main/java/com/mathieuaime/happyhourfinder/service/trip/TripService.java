package com.mathieuaime.happyhourfinder.service.trip;

import com.mathieuaime.happyhourfinder.model.trip.Trip;
import java.util.List;

/**
 * The Trip service interface.
 */
public interface TripService {

  /**
   * Generates a random trip sorted by happy hour.
   *
   * @param request the generation request
   * @return an optional of the generated trip
   */
  Trip generate(int count, List<String> mandatoryBars);
}
