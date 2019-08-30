package com.mathieuaime.happyhourfinder.service.trip.impl;

import static com.mathieuaime.happyhourfinder.comparator.BarComparator.minutesUntil;
import static com.mathieuaime.happyhourfinder.service.trip.impl.TestData.Bars.BAR_1;
import static com.mathieuaime.happyhourfinder.service.trip.impl.TestData.Bars.BAR_2;
import static com.mathieuaime.happyhourfinder.service.trip.impl.TestData.Bars.BAR_3;
import static com.mathieuaime.happyhourfinder.service.trip.impl.TestData.Bars.BAR_4;
import static com.mathieuaime.happyhourfinder.service.trip.impl.TestData.Bars.BAR_5;
import static com.mathieuaime.happyhourfinder.service.trip.impl.TestData.Bars.BAR_6;
import static com.mathieuaime.happyhourfinder.service.trip.impl.TestData.Bars.BAR_7;
import static com.mathieuaime.happyhourfinder.facade.trip.request.GenerateTripRequest.byCount;
import static com.mathieuaime.happyhourfinder.facade.trip.request.GenerateTripRequest.byCountAndMandatoryBars;
import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.ImmutableList;
import com.mathieuaime.happyhourfinder.model.bar.Bar;
import com.mathieuaime.happyhourfinder.model.bar.HappyHour;
import com.mathieuaime.happyhourfinder.model.trip.Trip;
import com.mathieuaime.happyhourfinder.repository.bar.BarDao;
import com.mathieuaime.happyhourfinder.service.trip.TripService;
import java.time.LocalTime;
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
    Trip trip = mockTripService.generate(byCount(-1));
    assertThat(trip.getBars()).isEmpty();
  }

  @Test
  public void generateATripWithNullBars() {
    Trip trip = mockTripService.generate(byCountAndMandatoryBars(-1, null));
    assertThat(trip.getBars()).isEmpty();
  }

  @Test
  public void generateATripWithZeroBar() {
    Trip trip = mockTripService.generate(byCount(0));
    assertThat(trip.getBars()).isEmpty();
  }

  @Test
  public void generateATripWithUnknownBar() {
    Trip trip = mockTripService.generate(byCountAndMandatoryBars(1, ImmutableList.of(8L)));
    assertThat(trip.getBars()).hasSize(1);
    assertThat(trip.getBars().get(0).getId()).isNotEqualTo(8L);
  }

  @Test
  public void generateATripWithBarWithoutHappyHour() {
    Trip trip = mockTripService.generate(byCountAndMandatoryBars(1, ImmutableList.of(7L)));
    assertThat(trip.getBars()).hasSize(1);
    assertThat(trip.getBars()).doesNotContain(BAR_7);
  }

  @Test
  public void generateATripWithOneBar() {
    Trip trip = mockTripService.generate(byCount(1));
    assertThat(trip.getBars()).hasSize(1);
    assertThat(trip.getBars()).containsAnyOf(BAR_1, BAR_2, BAR_3, BAR_4, BAR_5, BAR_6);
  }

  @Test
  public void generateATripWithMoreBarsThanInDb() {
    Trip trip = mockTripService.generate(byCount(10));
    assertThat(trip.getBars()).hasSize(6);
    assertThat(trip.getBars()).contains(BAR_1, BAR_2, BAR_3, BAR_4, BAR_5, BAR_6);
  }

  @Test
  public void generateATripWithNotEnoughBars() {
    Trip trip = mockTripService.generate(byCountAndMandatoryBars(1, ImmutableList.of(1L, 2L)));
    assertThat(trip.getBars()).isEmpty();
  }

  @Test
  public void generateATripWithOnlyMandatoryBars() {
    Trip trip = mockTripService.generate(byCountAndMandatoryBars(2, ImmutableList.of(1L, 2L)));
    assertThat(trip.getBars()).hasSize(2);
    assertThat(trip.getBars()).contains(BAR_1, BAR_2);
  }

  @Test
  public void generateATripWithConsecutiveHappyHours() {
    Trip trip = mockTripService.generate(byCount(3));

    List<Bar> bars = trip.getBars();
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
    Trip trip = mockTripService.generate(byCountAndMandatoryBars(5, ImmutableList.of(1L)));

    List<Bar> bars = trip.getBars();
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