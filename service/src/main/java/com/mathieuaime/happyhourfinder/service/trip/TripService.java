package com.mathieuaime.happyhourfinder.service.trip;

import com.mathieuaime.happyhourfinder.model.trip.Trip;
import com.mathieuaime.happyhourfinder.facade.trip.request.GenerateTripRequest;

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
  Trip generate(GenerateTripRequest request);
}
