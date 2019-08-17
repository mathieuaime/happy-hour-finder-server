package com.mathieuaime.happyhourfinder.trip.service;

import com.mathieuaime.happyhourfinder.trip.model.Trip;
import java.util.List;
import java.util.Optional;

public interface TripService {

  Optional<Trip> generate(int nbBars, List<Long> barIds);

  Optional<Trip> generateAndSortedByHappyHour(int nbBars, List<Long> barIds);
}
