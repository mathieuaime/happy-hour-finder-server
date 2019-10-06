package com.mathieuaime.happyhourfinder.service.trip.impl;

import com.mathieuaime.happyhourfinder.model.bar.Bar;
import com.mathieuaime.happyhourfinder.model.bar.HappyHour;
import java.time.Duration;
import java.time.LocalTime;

interface TestData {

  interface Bars {

    Bar BAR_1 = new Bar().id(1L).name("Bar1").happyHour(HappyHours.HAPPY_HOUR_1);
    Bar BAR_2 = new Bar().id(2L).name("Bar2").happyHour(HappyHours.HAPPY_HOUR_2);
    Bar BAR_3 = new Bar().id(3L).name("Bar3").happyHour(HappyHours.HAPPY_HOUR_3);
    Bar BAR_4 = new Bar().id(4L).name("Bar4").happyHour(HappyHours.HAPPY_HOUR_4);
    Bar BAR_5 = new Bar().id(5L).name("Bar5").happyHour(HappyHours.HAPPY_HOUR_5);
    Bar BAR_6 = new Bar().id(6L).name("Bar6").happyHour(HappyHours.HAPPY_HOUR_6);
    Bar BAR_7 = new Bar().id(7L).name("Bar7");
  }

  interface HappyHours {

    HappyHour HAPPY_HOUR_1 =
        new HappyHour().begin(LocalTime.of(12, 0)).duration(Duration.ofHours(1));
    HappyHour HAPPY_HOUR_2 =
        new HappyHour().begin(LocalTime.of(14, 0)).duration(Duration.ofHours(1));
    HappyHour HAPPY_HOUR_3 =
        new HappyHour().begin(LocalTime.of(15, 0)).duration(Duration.ofHours(2));
    HappyHour HAPPY_HOUR_4 =
        new HappyHour().begin(LocalTime.of(15, 30)).duration(Duration.ofHours(1));
    HappyHour HAPPY_HOUR_5 =
        new HappyHour().begin(LocalTime.of(23, 0)).duration(Duration.ofHours(3));
    HappyHour HAPPY_HOUR_6 =
        new HappyHour().begin(LocalTime.of(1, 0)).duration(Duration.ofHours(1));
  }
}
