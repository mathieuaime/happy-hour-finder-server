package com.mathieuaime.happyhourfinder.bar.service.impl;

import com.mathieuaime.happyhourfinder.bar.service.GMapsService;
import java.time.Duration;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
public class GMapsServiceImpl implements GMapsService {

  @Override
  public Duration timeToGo(Point from, Point to) {
    throw new UnsupportedOperationException();
  }
}
