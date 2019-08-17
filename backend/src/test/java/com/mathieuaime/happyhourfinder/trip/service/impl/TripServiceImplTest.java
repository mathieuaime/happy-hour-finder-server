package com.mathieuaime.happyhourfinder.trip.service.impl;

import static com.mathieuaime.happyhourfinder.bar.comparator.BarComparator.minutesUntil;
import static com.mathieuaime.happyhourfinder.trip.service.impl.TestData.Bars.BAR_1;
import static com.mathieuaime.happyhourfinder.trip.service.impl.TestData.Bars.BAR_2;
import static com.mathieuaime.happyhourfinder.trip.service.impl.TestData.Bars.BAR_3;
import static com.mathieuaime.happyhourfinder.trip.service.impl.TestData.Bars.BAR_4;
import static com.mathieuaime.happyhourfinder.trip.service.impl.TestData.Bars.BAR_5;
import static com.mathieuaime.happyhourfinder.trip.service.impl.TestData.Bars.BAR_6;
import static com.mathieuaime.happyhourfinder.trip.service.impl.TestData.Bars.BAR_7;
import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.ImmutableList;
import com.mathieuaime.happyhourfinder.bar.dao.BarDao;
import com.mathieuaime.happyhourfinder.bar.model.Bar;
import com.mathieuaime.happyhourfinder.bar.model.HappyHour;
import com.mathieuaime.happyhourfinder.trip.model.Trip;
import com.mathieuaime.happyhourfinder.trip.service.TripService;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TripServiceImplTest {

  private static final List<Bar> BARS =
      ImmutableList.of(BAR_1, BAR_2, BAR_3, BAR_4, BAR_5, BAR_6, BAR_7);

  @Mock
  private BarDao barDao;

  private TripService mockTripService;

  @Before
  public void setUp() {
    mockTripService = new TripServiceImpl(barDao);
    Mockito.when(barDao.findAll()).thenReturn(BARS);
    LongStream.range(0, BARS.size()).forEach(
        i -> Mockito.when(barDao.findById(i + 1)).thenReturn(Optional.of(BARS.get((int) i))));
    Mockito.when(barDao.findById(8L)).thenReturn(Optional.empty());
  }

  @Test
  public void generateATripWithNegativeNumberOfBar() {
    Optional<Trip> trip = mockTripService.generate(-1, Collections.emptyList());
    assertThat(trip).isNotPresent();
  }

  @Test
  public void generateATripWithNullBars() {
    Optional<Trip> trip = mockTripService.generate(0, null);
    assertThat(trip).isNotPresent();
  }

  @Test
  public void generateATripWithZeroBar() {
    Optional<Trip> trip = mockTripService.generate(0, Collections.emptyList());
    assertThat(trip).isPresent();
    assertThat(trip.get().getBars()).isEmpty();
  }

  @Test
  public void generateATripWithUnknownBar() {
    Optional<Trip> trip = mockTripService.generate(1, ImmutableList.of(8L));
    assertThat(trip).isPresent();
    assertThat(trip.get().getBars()).hasSize(1);
    assertThat(trip.get().getBars().get(0).getId()).isNotEqualTo(8L);
  }

  @Test
  public void generateATripWithOneBar() {
    Optional<Trip> trip = mockTripService.generate(1, Collections.emptyList());

    assertThat(trip).isPresent();
    assertThat(trip.get().getBars()).hasSize(1);
    assertThat(trip.get().getBars()).containsAnyOf(BAR_1, BAR_2, BAR_3, BAR_4, BAR_5, BAR_6, BAR_7);
  }

  @Test
  public void generateATripWithTenBars() {
    Optional<Trip> trip = mockTripService.generate(10, Collections.emptyList());

    assertThat(trip).isPresent();
    assertThat(trip.get().getBars()).hasSize(6);
    assertThat(trip.get().getBars()).contains(BAR_1, BAR_2, BAR_3, BAR_4, BAR_5, BAR_6);
  }

  @Test
  public void generateATripWithNotEnoughBars() {
    Optional<Trip> trip = mockTripService.generate(1, ImmutableList.of(1L, 2L));
    assertThat(trip).isNotPresent();
  }

  @Test
  public void generateATripWithOnlyMandatoryBars() {
    Optional<Trip> trip = mockTripService.generate(2, ImmutableList.of(1L, 2L));

    assertThat(trip).isPresent();
    assertThat(trip.get().getBars()).hasSize(2);
    assertThat(trip.get().getBars()).contains(BAR_1, BAR_2);
  }

  @Test
  public void generateATripWithMandatoryBars() {
    Optional<Trip> trip = mockTripService.generate(3, ImmutableList.of(1L, 2L));

    assertThat(trip).isPresent();
    assertThat(trip.get().getBars()).hasSize(3);
    assertThat(trip.get().getBars()).contains(BAR_1, BAR_2);
  }

  @Test
  public void generateATripWithHappyHourNegativeNumberOfBar() {
    Optional<Trip> trip = mockTripService.generateAndSortedByHappyHour(-1, Collections.emptyList());
    assertThat(trip).isNotPresent();
  }

  @Test
  public void generateATripWithHappyHourWithNullBars() {
    Optional<Trip> trip = mockTripService.generateAndSortedByHappyHour(0, null);
    assertThat(trip).isNotPresent();
  }

  @Test
  public void generateATripWithHappyHourWithBarWithoutHappyHour() {
    Optional<Trip> trip = mockTripService.generateAndSortedByHappyHour(1, ImmutableList.of(7L));
    assertThat(trip).isPresent();
    assertThat(trip.get().getBars()).hasSize(1);
    assertThat(trip.get().getBars()).doesNotContain(BAR_7);
  }

  @Test
  public void generateATripWithConsecutiveHappyHours() {
    Optional<Trip> trip = mockTripService.generateAndSortedByHappyHour(3, Collections.emptyList());

    assertThat(trip).isPresent();
    List<Bar> bars = trip.get().getBars();
    assertThat(bars).hasSize(3);

    HappyHour hh1 = bars.get(0).getHappyHour();
    HappyHour hh2 = bars.get(1).getHappyHour();
    HappyHour hh3 = bars.get(2).getHappyHour();

    LocalTime now = LocalTime.now();

    assertThat(minutesUntil(hh1.getBegin(), now)).isLessThan(minutesUntil(hh2.getBegin(), now));
    assertThat(minutesUntil(hh2.getBegin(), now)).isLessThan(minutesUntil(hh3.getBegin(), now));
  }

  @Test
  public void generateATripWithConsecutiveHappyHoursAndMandatoryBars() {
    Optional<Trip> trip = mockTripService.generateAndSortedByHappyHour(5, ImmutableList.of(1L));

    assertThat(trip).isPresent();
    List<Bar> bars = trip.get().getBars();
    assertThat(bars).hasSize(5);
    assertThat(bars).contains(BAR_1);

    HappyHour hh1 = bars.get(0).getHappyHour();
    HappyHour hh2 = bars.get(1).getHappyHour();
    HappyHour hh3 = bars.get(2).getHappyHour();
    HappyHour hh4 = bars.get(3).getHappyHour();
    HappyHour hh5 = bars.get(4).getHappyHour();

    LocalTime now = LocalTime.now();

    assertThat(minutesUntil(hh1.getBegin(), now)).isLessThan(minutesUntil(hh2.getBegin(), now));
    assertThat(minutesUntil(hh2.getBegin(), now)).isLessThan(minutesUntil(hh3.getBegin(), now));
    assertThat(minutesUntil(hh3.getBegin(), now)).isLessThan(minutesUntil(hh4.getBegin(), now));
    assertThat(minutesUntil(hh4.getBegin(), now)).isLessThan(minutesUntil(hh5.getBegin(), now));
  }
}