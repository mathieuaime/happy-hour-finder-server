package com.mathieuaime.happyhourfinder.service.trip.impl;

import com.mathieuaime.happyhourfinder.model.bar.Bar;
import com.mathieuaime.happyhourfinder.model.bar.HappyHour;
import java.time.Duration;
import java.time.LocalTime;

interface TestData {

  interface Bars {

    Bar BAR_1 = Bar.builder().id(1L).name("Bar1").happyHour(HappyHours.HAPPY_HOUR_1).build();
    Bar BAR_2 = Bar.builder().id(2L).name("Bar2").happyHour(HappyHours.HAPPY_HOUR_2).build();
    Bar BAR_3 = Bar.builder().id(3L).name("Bar3").happyHour(HappyHours.HAPPY_HOUR_3).build();
    Bar BAR_4 = Bar.builder().id(4L).name("Bar4").happyHour(HappyHours.HAPPY_HOUR_4).build();
    Bar BAR_5 = Bar.builder().id(5L).name("Bar5").happyHour(HappyHours.HAPPY_HOUR_5).build();
    Bar BAR_6 = Bar.builder().id(6L).name("Bar6").happyHour(HappyHours.HAPPY_HOUR_6).build();
    Bar BAR_7 = Bar.builder().id(7L).name("Bar7").build();
  }

  interface HappyHours {

    HappyHour HAPPY_HOUR_1 =
        HappyHour.builder().begin(LocalTime.of(12, 0)).duration(Duration.ofHours(1)).build();
    HappyHour HAPPY_HOUR_2 =
        HappyHour.builder().begin(LocalTime.of(14, 0)).duration(Duration.ofHours(1)).build();
    HappyHour HAPPY_HOUR_3 =
        HappyHour.builder().begin(LocalTime.of(15, 0)).duration(Duration.ofHours(2)).build();
    HappyHour HAPPY_HOUR_4 =
        HappyHour.builder().begin(LocalTime.of(15, 30)).duration(Duration.ofHours(1)).build();
    HappyHour HAPPY_HOUR_5 =
        HappyHour.builder().begin(LocalTime.of(23, 0)).duration(Duration.ofHours(3)).build();
    HappyHour HAPPY_HOUR_6 =
        HappyHour.builder().begin(LocalTime.of(1, 0)).duration(Duration.ofHours(1)).build();
  }
}
