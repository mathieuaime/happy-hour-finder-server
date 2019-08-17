package com.mathieuaime.happyhourfinder.trip.service.impl;

import com.google.common.collect.Lists;
import com.mathieuaime.happyhourfinder.bar.comparator.BarComparator;
import com.mathieuaime.happyhourfinder.bar.dao.BarDao;
import com.mathieuaime.happyhourfinder.bar.model.Bar;
import com.mathieuaime.happyhourfinder.trip.model.Trip;
import com.mathieuaime.happyhourfinder.trip.service.TripService;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public class TripServiceImpl implements TripService {

  private final BarDao barDao;

  @Autowired
  TripServiceImpl(BarDao barDao) {
    this.barDao = barDao;
  }

  @Override
  public Optional<Trip> generate(int nbBars, List<Long> mandatoryBars) {
    if (mandatoryBars == null || nbBars < mandatoryBars.size()) {
      return Optional.empty();
    }

    List<Bar> bars = mandatoryBars.stream()
        .map(barDao::findById)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(Collectors.toList());
    addBarsFromDb(nbBars, bars);

    return Optional.of(Trip.builder().bars(bars).build());
  }

  @Override
  public Optional<Trip> generateAndSortedByHappyHour(int nbBars, List<Long> mandatoryBars) {
    if (mandatoryBars == null || nbBars < mandatoryBars.size()) {
      return Optional.empty();
    }

    List<Bar> bars = mandatoryBars.stream()
        .map(barDao::findById)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .filter(bar -> bar.getHappyHour() != null)
        .collect(Collectors.toList());
    addBarsFromDb(nbBars, bars);

    bars.sort(BarComparator.compareByHappyHour(LocalTime.now()));

    return Optional.of(Trip.builder().bars(bars).build());
  }

  private void addBarsFromDb(int nbBars, List<Bar> mandatoryBars) {
    List<Bar> bars = Lists.newArrayList(barDao.findAll().iterator());

    Collections.shuffle(bars);

    int limitBars = Math.min(nbBars, bars.size()) - mandatoryBars.size();

    bars.stream()
        .filter(bar -> bar.getHappyHour() != null)
        .filter(bar -> !mandatoryBars.contains(bar))
        .limit(limitBars)
        .forEach(mandatoryBars::add);
  }
}
