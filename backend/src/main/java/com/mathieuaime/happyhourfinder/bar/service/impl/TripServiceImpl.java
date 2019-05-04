package com.mathieuaime.happyhourfinder.bar.service.impl;

import com.google.common.base.Preconditions;
import com.mathieuaime.happyhourfinder.bar.dao.BarDao;
import com.mathieuaime.happyhourfinder.bar.model.Bar;
import com.mathieuaime.happyhourfinder.bar.model.Trip;
import com.mathieuaime.happyhourfinder.bar.service.GMapsService;
import com.mathieuaime.happyhourfinder.bar.service.TripService;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.modelmapper.internal.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public class TripServiceImpl implements TripService {

  private final BarDao barDao;

  private final GMapsService gMapsService;

  private static final Comparator<Bar> COMPARATOR_BY_HAPPY_HOUR = (o1, o2) -> o1.getHappyHour()
      .getBegin().plus(o1.getHappyHour().getDuration()).compareTo(o2.getHappyHour().getBegin());

  @Autowired
  TripServiceImpl(BarDao barDao, GMapsService gMapsService) {
    this.barDao = barDao;
    this.gMapsService = gMapsService;
  }

  @Override
  public Trip generate(int nbBars, Bar... mandatoryBars) {
    checkPreconditions(nbBars, mandatoryBars);

    Trip trip = new Trip();
    List<Bar> bars = Arrays.asList(mandatoryBars);
    bars.forEach(trip::addBar);

    addBarsFromDb(nbBars, trip, bars);
    return trip;
  }

  @Override
  public Trip generateWithHappyHour(int nbBars, Bar... mandatoryBars) {
    checkPreconditions(nbBars, mandatoryBars);

    Trip trip = new Trip();
    List<Bar> bars = Arrays.asList(mandatoryBars);
    bars.forEach(trip::addBar);

    List<Bar> barsFromDb = Lists.from(barDao.findAll().iterator());

    barsFromDb.sort(COMPARATOR_BY_HAPPY_HOUR);

    int limitBars = Math.min(nbBars, bars.size()) - trip.getCount();

    bars.stream()
        .filter(bar -> !bars.contains(bar))
        .limit(limitBars)
        .forEach(trip::addBar);

    return new Trip();
  }

  private void checkPreconditions(int nbBars, Bar[] mandatoryBars) {
    Preconditions.checkNotNull(mandatoryBars);
    Preconditions.checkArgument(nbBars >= mandatoryBars.length,
        "The number of expected bars should be greater or equal of the number of the mandatory bars");
  }

  private void addBarsFromDb(int nbBars, Trip trip, List<Bar> mandatoryBars) {
    List<Bar> bars = Lists.from(barDao.findAll().iterator());

    Collections.shuffle(bars);

    int limitBars = Math.min(nbBars, bars.size()) - trip.getCount();

    bars.stream()
        .filter(bar -> !mandatoryBars.contains(bar))
        .limit(limitBars)
        .forEach(trip::addBar);
  }
}
