package com.mathieuaime.happyhourfinder.comparator;

import com.mathieuaime.happyhourfinder.model.bar.Bar;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BarComparator {

  public static Comparator<Bar> compareByHappyHour(LocalTime ref) {
    return (o1, o2) -> {
      long untilHH1 = minutesUntil(o1.getHappyHour().getBegin(), ref);
      long untilHH2 = minutesUntil(o2.getHappyHour().getBegin(), ref);
      return Long.compare(untilHH1, untilHH2);
    };
  }

  public static long minutesUntil(LocalTime localTime, LocalTime ref) {
    return Math.floorMod(ref.until(localTime, ChronoUnit.MINUTES), 86400);
  }
}
