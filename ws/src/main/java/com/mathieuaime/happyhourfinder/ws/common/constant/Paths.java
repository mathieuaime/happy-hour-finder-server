package com.mathieuaime.happyhourfinder.ws.common.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class Paths {

  public static final String VERSION = "/api/1.0/";
  public static final String STATUS = "status/check";

  @UtilityClass
  public static final class Bar {
    public static final String BARS = "bars/";
  }

  @UtilityClass
  public static final class Trip {
    public static final String TRIPS = "trips/";
  }
}
