package com.mathieuaime.happyhourfinder.facade.trip;

import com.mathieuaime.happyhourfinder.api.trip.TripDto;
import com.mathieuaime.happyhourfinder.facade.trip.request.GenerateTripRequest;

/**
 * The Trip service interface.
 */
public interface TripFacade {

  /**
   * Generates a random trip sorted by happy hour.
   *
   * @param request the generation request
   * @return an optional of the generated trip
   */
  TripDto generate(GenerateTripRequest request);
}
