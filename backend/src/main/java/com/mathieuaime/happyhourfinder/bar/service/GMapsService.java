package com.mathieuaime.happyhourfinder.bar.service;

import java.time.Duration;
import org.locationtech.jts.geom.Point;

public interface GMapsService {

  Duration timeToGo(Point from, Point to);
}
