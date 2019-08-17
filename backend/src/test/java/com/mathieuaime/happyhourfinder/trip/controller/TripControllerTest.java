package com.mathieuaime.happyhourfinder.trip.controller;

import static com.mathieuaime.happyhourfinder.common.constants.Paths.STATUS;
import static com.mathieuaime.happyhourfinder.common.constants.Paths.Trip.HAPPY_HOUR;
import static com.mathieuaime.happyhourfinder.common.constants.Paths.Trip.TRIPS;
import static com.mathieuaime.happyhourfinder.common.constants.Paths.VERSION;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.common.collect.ImmutableList;
import com.mathieuaime.happyhourfinder.bar.dto.BarDto;
import com.mathieuaime.happyhourfinder.bar.dto.HappyHourDto;
import com.mathieuaime.happyhourfinder.bar.model.Bar;
import com.mathieuaime.happyhourfinder.bar.model.HappyHour;
import com.mathieuaime.happyhourfinder.trip.dto.TripDto;
import com.mathieuaime.happyhourfinder.trip.mapper.TripMapper;
import com.mathieuaime.happyhourfinder.trip.model.Trip;
import com.mathieuaime.happyhourfinder.trip.service.TripService;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(TripController.class)
public class TripControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TripService tripService;

  @MockBean
  private TripMapper tripMapper;

  private static final GeometryFactory geometryFactory =
      new GeometryFactory(new PrecisionModel(), 26910);

  private static final Point POINT_1 = geometryFactory.createPoint(new Coordinate(1, 2));
  private static final Point POINT_2 = geometryFactory.createPoint(new Coordinate(2, 3));

  private static final HappyHour HAPPY_HOUR_1 =
      HappyHour.builder().begin(LocalTime.of(16, 0)).duration(Duration.ofHours(1)).build();

  private static final Bar BAR_1 =
      Bar.builder().id(1L).name("Bar1").coordinates(POINT_1).happyHour(HAPPY_HOUR_1).build();

  private static final HappyHourDto HAPPY_HOUR_DTO_1 =
      HappyHourDto.builder().begin("16:00").duration("PT1H").build();

  private static final BarDto BAR_DTO_1 =
      BarDto.builder().id(1L).name("Bar1").coordinates(POINT_1).happyHour(HAPPY_HOUR_DTO_1).build();

  private static final HappyHour HAPPY_HOUR_2 =
      HappyHour.builder().begin(LocalTime.of(20, 0)).duration(Duration.ofHours(1)).build();

  private static final Bar BAR_2 =
      Bar.builder().id(2L).name("Bar2").coordinates(POINT_2).happyHour(HAPPY_HOUR_2).build();

  private static final HappyHourDto HAPPY_HOUR_DTO_2 =
      HappyHourDto.builder().begin("20:00").duration("PT1H").build();

  private static final BarDto BAR_DTO_2 =
      BarDto.builder().id(2L).name("Bar2").coordinates(POINT_2).happyHour(HAPPY_HOUR_DTO_2).build();

  private static final Trip TRIP_1 = Trip.builder().bars(ImmutableList.of(BAR_1)).build();

  private static final Trip TRIP_2 = Trip.builder().bars(ImmutableList.of(BAR_1, BAR_2)).build();

  private static final TripDto TRIP_DTO_1 =
      TripDto.builder().bars(Collections.singletonList(BAR_DTO_1)).build();

  private static final TripDto TRIP_DTO_2 =
      TripDto.builder().bars(ImmutableList.of(BAR_DTO_1, BAR_DTO_2)).build();

  @Before
  public void setUp() throws Exception {
    Mockito.when(tripMapper.convertToDto(TRIP_1)).thenReturn(TRIP_DTO_1);
    Mockito.when(tripMapper.convertToDto(TRIP_2)).thenReturn(TRIP_DTO_2);

    when(tripService.generate(Mockito.eq(1), Mockito.anyList())).thenReturn(Optional.of(TRIP_1));
    when(tripService.generateAndSortedByHappyHour(Mockito.eq(1), Mockito.anyList()))
        .thenReturn(Optional.of(TRIP_1));
    when(tripService.generate(Mockito.eq(2), Mockito.anyList())).thenReturn(Optional.of(TRIP_2));
    when(tripService.generateAndSortedByHappyHour(Mockito.eq(2), Mockito.anyList()))
        .thenReturn(Optional.of(TRIP_2));
  }

  @After
  public void tearDown() {
    verifyNoMoreInteractions(tripService);
  }

  @Test
  public void getStatus() throws Exception {
    mockMvc.perform(get(VERSION + TRIPS + STATUS)
        .contentType(MediaType.TEXT_PLAIN))
        .andExpect(status().isOk())
        .andExpect(content().string("working"));
  }

  @Test
  public void generateTripWithBadValue() throws Exception {
    when(tripService.generate(-1, Collections.emptyList())).thenReturn(Optional.empty());

    mockMvc.perform(get(VERSION + TRIPS)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .param("count", "-1"))
        .andExpect(status().is5xxServerError());

    verify(tripService, times(1)).generate(-1, Collections.emptyList());
  }

  @Test
  public void generateTripWithDefaultValue() throws Exception {
    mockMvc.perform(get(VERSION + TRIPS)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.bars", hasSize(1)))
        .andExpect(jsonPath("$.bars[0].id", is(1)))
        .andExpect(jsonPath("$.bars[0].name", is("Bar1")))
        .andExpect(jsonPath("$.bars[0].coordinates", is("POINT (1.0 2.0)")))
        .andExpect(jsonPath("$.bars[0].happyHour.begin", is("16:00")))
        .andExpect(jsonPath("$.bars[0].happyHour.duration", is("PT1H")));

    verify(tripService, times(1)).generate(1, Collections.emptyList());
  }

  @Test
  public void generateTripWithCount() throws Exception {
    mockMvc.perform(get(VERSION + TRIPS)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .param("count", "2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.bars", hasSize(2)));

    verify(tripService, times(1)).generate(2, Collections.emptyList());
  }

  @Test
  public void generateTripWithMandatoryBar() throws Exception {
    mockMvc.perform(get(VERSION + TRIPS)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .param("count", "2")
        .param("bars", "2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.bars", hasSize(2)));

    verify(tripService, times(1)).generate(2, ImmutableList.of(2L));
  }

  @Test
  public void generateTripSortedByHappyHourWithBadValue() throws Exception {
    when(tripService.generateAndSortedByHappyHour(-1, Collections.emptyList()))
        .thenReturn(Optional.empty());

    mockMvc.perform(get(VERSION + TRIPS + HAPPY_HOUR)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .param("count", "-1"))
        .andExpect(status().is5xxServerError());

    verify(tripService, times(1)).generateAndSortedByHappyHour(-1, Collections.emptyList());
  }

  @Test
  public void generateTripSortedByHappyHourWithDefaultValue() throws Exception {
    mockMvc.perform(get(VERSION + TRIPS + HAPPY_HOUR)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.bars", hasSize(1)))
        .andExpect(jsonPath("$.bars[0].id", is(1)))
        .andExpect(jsonPath("$.bars[0].name", is("Bar1")))
        .andExpect(jsonPath("$.bars[0].coordinates", is("POINT (1.0 2.0)")))
        .andExpect(jsonPath("$.bars[0].happyHour.begin", is("16:00")))
        .andExpect(jsonPath("$.bars[0].happyHour.duration", is("PT1H")));

    verify(tripService, times(1)).generateAndSortedByHappyHour(1, Collections.emptyList());
  }

  @Test
  public void generateTripSortedByHappyHourWithCount() throws Exception {
    mockMvc.perform(get(VERSION + TRIPS + HAPPY_HOUR)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .param("count", "2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.bars", hasSize(2)));

    verify(tripService, times(1)).generateAndSortedByHappyHour(2, Collections.emptyList());
  }

  @Test
  public void generateTripSortedByHappyHourWithMandatoryBar() throws Exception {
    mockMvc.perform(get(VERSION + TRIPS + HAPPY_HOUR)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .param("count", "2")
        .param("bars", "2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.bars", hasSize(2)));

    verify(tripService, times(1)).generateAndSortedByHappyHour(2, ImmutableList.of(2L));
  }
}