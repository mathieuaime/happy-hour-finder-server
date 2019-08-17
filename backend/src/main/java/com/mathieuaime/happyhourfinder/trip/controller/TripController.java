package com.mathieuaime.happyhourfinder.trip.controller;

import com.mathieuaime.happyhourfinder.common.constants.Paths;
import com.mathieuaime.happyhourfinder.common.constants.Paths.Trip;
import com.mathieuaime.happyhourfinder.trip.dto.TripDto;
import com.mathieuaime.happyhourfinder.trip.exception.TripGenerationExecption;
import com.mathieuaime.happyhourfinder.trip.mapper.TripMapper;
import com.mathieuaime.happyhourfinder.trip.service.TripService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Paths.VERSION + Paths.Trip.TRIPS)
public class TripController {

  private final TripService tripService;
  private final TripMapper tripMapper;

  @Autowired
  public TripController(TripService tripService, TripMapper tripMapper) {
    this.tripService = tripService;
    this.tripMapper = tripMapper;
  }

  @GetMapping(Paths.STATUS)
  public String status() {
    return "working";
  }

  @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public TripDto generateTrip(
      @RequestParam(value = "count", required = false, defaultValue = "1") int count,
      @RequestParam(value = "bars", required = false, defaultValue = "") List<Long> barIds) {

    return tripService.generate(count, barIds)
        .map(tripMapper::convertToDto)
        .orElseThrow(TripGenerationExecption::new);
  }

  @GetMapping(Trip.HAPPY_HOUR)
  public TripDto generateTripSortedByHappyHour(
      @RequestParam(value = "count", required = false, defaultValue = "1") int count,
      @RequestParam(value = "bars", required = false, defaultValue = "") List<Long> barIds) {

    return tripService.generateAndSortedByHappyHour(count, barIds)
        .map(tripMapper::convertToDto)
        .orElseThrow(TripGenerationExecption::new);
  }
}
