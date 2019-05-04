package com.mathieuaime.happyhourfinder.bar.service;

import com.mathieuaime.happyhourfinder.bar.model.Bar;
import com.mathieuaime.happyhourfinder.bar.model.Trip;


public interface TripService {

  Trip generate(int nbBars, Bar... bars);

  Trip generateWithHappyHour(int nbBars, Bar... bars);
}
