package com.mathieuaime.happyhourfinder.service.trip.impl;

import com.google.common.collect.Lists;
import com.mathieuaime.happyhourfinder.comparator.BarComparator;
import com.mathieuaime.happyhourfinder.model.bar.Bar;
import com.mathieuaime.happyhourfinder.model.trip.Trip;
import com.mathieuaime.happyhourfinder.repository.bar.BarDao;
import com.mathieuaime.happyhourfinder.service.trip.TripService;
import com.mathieuaime.happyhourfinder.service.trip.request.GenerateTripRequest;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripServiceImpl implements TripService {

  private final BarDao barDao;

  @Autowired
  TripServiceImpl(BarDao barDao) {
    this.barDao = barDao;
  }

  @Override
  public Trip generate(GenerateTripRequest request) {
    List<Long> mandatoryBars = request.getMandatoryBars();

    if (mandatoryBars == null || request.getCount() < mandatoryBars.size()) {
      return Trip.empty();
    }

    List<Bar> bars = mandatoryBars.stream()
        .map(barDao::findById)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .filter(bar -> bar.getHappyHour() != null)
        .collect(Collectors.toList());

    bars.addAll(addBarsFromDb(request.getCount(), bars));

    bars.sort(BarComparator.compareByHappyHour(LocalTime.now()));

    return Trip.create(bars);
  }

  private List<Bar> addBarsFromDb(int count, List<Bar> actualBars) {
    List<Bar> bars = Lists.newArrayList(barDao.findAll().iterator());

    Collections.shuffle(bars);

    int limitBars = Math.min(count, bars.size()) - actualBars.size();

    return bars.stream()
        .filter(bar -> bar.getHappyHour() != null)
        .filter(bar -> !actualBars.contains(bar))
        .limit(limitBars)
        .collect(Collectors.toList());
  }
}
