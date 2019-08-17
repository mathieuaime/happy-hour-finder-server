package com.mathieuaime.happyhourfinder.trip.service;

import com.mathieuaime.happyhourfinder.trip.model.Trip;
import java.util.Optional;

/**
 * The Trip service interface.
 */
public interface TripService {

  /**
   * Generates a random trip.
   *
   * @param request the generation request
   * @return an optional of the generated trip
   */
  Optional<Trip> generate(GenerateTripRequest request);

  /**
   * Generates a random trip sorted by happy hour.
   *
   * @param request the generation request
   * @return an optional of the generated trip
   */
  Optional<Trip> generateAndSortedByHappyHour(GenerateTripRequest request);
}
