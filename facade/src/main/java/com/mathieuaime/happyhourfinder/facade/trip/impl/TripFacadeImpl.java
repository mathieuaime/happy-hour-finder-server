package com.mathieuaime.happyhourfinder.facade.trip.impl;

import com.mathieuaime.happyhourfinder.api.trip.TripDto;
import com.mathieuaime.happyhourfinder.facade.trip.TripFacade;
import com.mathieuaime.happyhourfinder.facade.trip.request.GenerateTripRequest;
import com.mathieuaime.happyhourfinder.mapper.trip.TripMapper;
import com.mathieuaime.happyhourfinder.service.trip.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripFacadeImpl implements TripFacade {

  private TripService tripService;
  private TripMapper tripMapper;

  @Autowired
  public TripFacadeImpl(TripService tripService, TripMapper tripMapper) {
    this.tripService = tripService;
    this.tripMapper = tripMapper;
  }

  @Override
  public TripDto generate(GenerateTripRequest request) {
    return tripMapper
        .convertToDto(tripService.generate(request.getCount(), request.getMandatoryBars()));
  }
}
