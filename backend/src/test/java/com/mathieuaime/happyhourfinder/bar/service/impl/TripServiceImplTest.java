package com.mathieuaime.happyhourfinder.bar.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import com.mathieuaime.happyhourfinder.bar.dao.BarDao;
import com.mathieuaime.happyhourfinder.bar.model.Bar;
import com.mathieuaime.happyhourfinder.bar.model.HappyHour;
import com.mathieuaime.happyhourfinder.bar.model.Trip;
import com.mathieuaime.happyhourfinder.bar.service.GMapsService;
import com.mathieuaime.happyhourfinder.bar.service.TripService;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TripServiceImplTest {

  private static final List<Bar> BARS = new ArrayList<>();

  private static final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(),
      26910);

  private static final Bar BAR_1 = Bar.builder().id(1L).name("Bar1")
      .happyHour(new HappyHour(LocalTime.of(12, 0), Duration.ofHours(1))).build();
  private static final Bar BAR_2 = Bar.builder().id(2L).name("Bar2")
      .happyHour(new HappyHour(LocalTime.of(14, 0), Duration.ofHours(1))).build();
  private static final Bar BAR_3 = Bar.builder().id(3L).name("Bar3")
      .happyHour(new HappyHour(LocalTime.of(15, 0), Duration.ofHours(2))).build();
  private static final Bar BAR_4 = Bar.builder().id(4L).name("Bar4")
      .happyHour(new HappyHour(LocalTime.of(15, 30), Duration.ofHours(1))).build();
  private static final Bar BAR_5 = Bar.builder().id(5L).name("Bar5")
      .happyHour(new HappyHour(LocalTime.of(23, 0), Duration.ofHours(3))).build();
  private static final Bar BAR_6 = Bar.builder().id(6L).name("Bar6")
      .happyHour(new HappyHour(LocalTime.of(1, 0), Duration.ofHours(1))).build();

  @Mock
  private BarDao barDao;

  @Mock
  private GMapsService gMapsService;

  private TripService mockTripService;

  @BeforeClass
  public static void setUpClass() {
    BARS.add(BAR_1);
    BARS.add(BAR_2);
    BARS.add(BAR_3);
    BARS.add(BAR_4);
    BARS.add(BAR_5);
    BARS.add(BAR_6);
  }

  @Before
  public void setUp() {
    mockTripService = new TripServiceImpl(barDao, gMapsService);
    Mockito.when(barDao.findAll()).thenReturn(BARS);
    Mockito.when(gMapsService.timeToGo(BAR_1.getCoordinates(), BAR_2.getCoordinates()))
        .thenReturn(Duration.ofMinutes(60));
    Mockito.when(gMapsService.timeToGo(BAR_2.getCoordinates(), BAR_3.getCoordinates()))
        .thenReturn(Duration.ofMinutes(30));
    Mockito.when(gMapsService.timeToGo(BAR_3.getCoordinates(), BAR_4.getCoordinates()))
        .thenReturn(Duration.ofMinutes(20));
    Mockito.when(gMapsService.timeToGo(BAR_4.getCoordinates(), BAR_5.getCoordinates()))
        .thenReturn(Duration.ofMinutes(50));
    Mockito.when(gMapsService.timeToGo(BAR_5.getCoordinates(), BAR_6.getCoordinates()))
        .thenReturn(Duration.ofMinutes(70));
  }

  @Test(expected = IllegalArgumentException.class)
  public void generateATripWithNegativeNumberOfBar() {
    mockTripService.generate(-1);
  }

  @Test
  public void generateATripWithZeroBar() {
    Trip trip = mockTripService.generate(0);
    assertThat(trip.getBars()).isEmpty();
  }

  @Test
  public void generateATripWithOneBar() {
    Trip trip = mockTripService.generate(1);

    assertThat(trip.getBars().size()).isEqualTo(1);
    assertThat(trip.getBars()).containsAnyOf(BARS.toArray(new Bar[]{}));
  }

  @Test
  public void generateATripWithTenBars() {
    Trip trip = mockTripService.generate(10);

    assertThat(trip.getBars().size()).isEqualTo(6);
    assertThat(trip.getBars()).contains(BARS.toArray(new Bar[]{}));
  }

  @Test(expected = IllegalArgumentException.class)
  public void generateATripWithNotEnoughBars() {
    Bar bar1 = BARS.get(0);
    Bar bar2 = BARS.get(1);
    mockTripService.generate(1, bar1, bar2);
  }

  @Test
  public void generateATripWithOnlyMandatoryBars() {
    Bar bar1 = BARS.get(0);
    Bar bar2 = BARS.get(1);
    Trip trip = mockTripService.generate(2, bar1, bar2);

    assertThat(trip.getBars().size()).isEqualTo(2);
    assertThat(trip.getBars()).contains(bar1, bar2);
  }

  @Test
  public void generateATripWithMandatoryBars() {
    Bar bar1 = BARS.get(0);
    Bar bar2 = BARS.get(1);
    Trip trip = mockTripService.generate(3, bar1, bar2);

    assertThat(trip.getBars().size()).isEqualTo(3);
    assertThat(trip.getBars()).contains(bar1, bar2);
  }

  @Test
  public void generateATripWithConsecutiveHappyHours() {
    Trip trip = mockTripService.generateWithHappyHour(3);

    HappyHour happyHour1 = trip.getBars().get(0).getHappyHour();
    HappyHour happyHour2 = trip.getBars().get(1).getHappyHour();
    HappyHour happyHour3 = trip.getBars().get(2).getHappyHour();
  }
}