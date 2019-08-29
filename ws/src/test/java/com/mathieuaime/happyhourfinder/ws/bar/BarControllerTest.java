<<<<<<< dev:backend/src/test/java/com/mathieuaime/happyhourfinder/bar/controller/BarControllerTest.java
package com.mathieuaime.happyhourfinder.bar.controller;

import static com.mathieuaime.happyhourfinder.common.constants.Paths.Bar.BARS;
import static com.mathieuaime.happyhourfinder.common.constants.Paths.STATUS;
import static com.mathieuaime.happyhourfinder.common.constants.Paths.VERSION;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mathieuaime.happyhourfinder.bar.dao.BarDao;
import com.mathieuaime.happyhourfinder.bar.dto.BarDto;
import com.mathieuaime.happyhourfinder.bar.dto.HappyHourDto;
import com.mathieuaime.happyhourfinder.bar.mapper.BarMapper;
import com.mathieuaime.happyhourfinder.bar.model.Bar;
import com.mathieuaime.happyhourfinder.bar.model.HappyHour;
import com.mathieuaime.happyhourfinder.bar.service.BarService;
import com.mathieuaime.happyhourfinder.type.IntegrationTest;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(BarController.class)
@Category(IntegrationTest.class)
public class BarControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BarService barService;

  @MockBean
  private BarMapper barMapper;

  @MockBean
  private BarDao barDao;

  private static final GeometryFactory geometryFactory =
      new GeometryFactory(new PrecisionModel(), 26910);

  private ObjectMapper objectMapper = new ObjectMapper();

  private static final Point POINT_1 = geometryFactory.createPoint(new Coordinate(1, 2));
  private static final Point POINT_2 = geometryFactory.createPoint(new Coordinate(2, 3));

  private static final HappyHour HAPPY_HOUR =
      HappyHour.builder().begin(LocalTime.of(16, 0)).duration(Duration.ofHours(1)).build();

  private static final Bar BAR_1 =
      Bar.builder().id(1L).name("Bar1").coordinates(POINT_1).happyHour(HAPPY_HOUR).build();
  private static final Bar BAR_2 = Bar.builder().id(2L).name("Bar2").coordinates(POINT_2).build();
  private static final Bar BAR_3 = Bar.builder().id(3L).name("Bar3").build();

  private static final HappyHourDto HAPPY_HOUR_DTO =
      HappyHourDto.builder().begin("16:00").duration("PT1H").build();

  private static final BarDto BAR_DTO_1 =
      BarDto.builder().id(1L).name("Bar1").coordinates(POINT_1).happyHour(HAPPY_HOUR_DTO).build();
  private static final BarDto BAR_DTO_2 =
      BarDto.builder().id(2L).name("Bar2").coordinates(POINT_2).build();
  private static final BarDto BAR_DTO_3 = BarDto.builder().id(3L).name("Bar3").build();

  @Before
  public void setUp() {
    Mockito.when(barMapper.convertToDto(BAR_1)).thenReturn(BAR_DTO_1);
    Mockito.when(barMapper.convertToDto(BAR_2)).thenReturn(BAR_DTO_2);
    Mockito.when(barMapper.convertToDto(BAR_3)).thenReturn(BAR_DTO_3);
    Mockito.when(barMapper.convertToEntity(BAR_DTO_1)).thenReturn(BAR_1);
    Mockito.when(barMapper.convertToEntity(BAR_DTO_2)).thenReturn(BAR_2);
    Mockito.when(barMapper.convertToEntity(BAR_DTO_3)).thenReturn(BAR_3);
  }

  @After
  public void tearDown() {
    verifyNoMoreInteractions(barService);
  }

  @Test
  public void getStatus() throws Exception {
    mockMvc.perform(get(VERSION + BARS + STATUS)
        .contentType(MediaType.TEXT_PLAIN))
        .andExpect(status().isOk())
        .andExpect(content().string("working"));
  }

  @Test
  public void getBars() throws Exception {
    Page<Bar> paginableBar = new PageImpl<>(Arrays.asList(BAR_1, BAR_2));

    when(barService.findAll(any(Pageable.class))).thenReturn(paginableBar);

    mockMvc.perform(get(VERSION + BARS)
        .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content", hasSize(2)))
        .andExpect(jsonPath("$.content[0].id", is(1)))
        .andExpect(jsonPath("$.content[0].name", is("Bar1")))
        .andExpect(jsonPath("$.content[0].coordinates", is("POINT (1.0 2.0)")))
        .andExpect(jsonPath("$.content[1].id", is(2)))
        .andExpect(jsonPath("$.content[1].name", is("Bar2")))
        .andExpect(jsonPath("$.content[1].coordinates", is("POINT (2.0 3.0)")));

    verify(barService, times(1)).findAll(any(Pageable.class));
  }

  @Test
  public void getBarById() throws Exception {
    Long barId = BAR_1.getId();

    when(barService.findById(barId)).thenReturn(Optional.of(BAR_1));

    mockMvc.perform(get(VERSION + BARS + "{id}", barId)
        .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.name", is("Bar1")))
        .andExpect(jsonPath("$.coordinates", is("POINT (1.0 2.0)")))
        .andExpect(jsonPath("$.happyHour.begin", is("16:00")))
        .andExpect(jsonPath("$.happyHour.duration", is("PT1H")));

    verify(barService, times(1)).findById(barId);
  }

  @Test
  public void getBarByIdNotFound() throws Exception {
    Long barId = BAR_1.getId();

    mockMvc.perform(get(VERSION + BARS + "{id}", 1L)
        .contentType(APPLICATION_JSON))
        .andExpect(status().isNotFound());

    verify(barService, times(1)).findById(barId);
  }

  @Test
  public void saveBar() throws Exception {

    when(barService.save(any(Bar.class))).then(invocationOnMock -> BAR_1);

    mockMvc.perform(post(VERSION + BARS)
        .contentType(APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(BAR_DTO_1)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.name", is("Bar1")))
        .andExpect(jsonPath("$.coordinates", is("POINT (1.0 2.0)")));

    verify(barService, times(1)).save(any(Bar.class));
    verifyNoMoreInteractions(barService);
  }

  @Test
  public void saveBarWithNullCoordinates() throws Exception {
    when(barService.save(any(Bar.class))).then(invocationOnMock -> BAR_3);

    mockMvc.perform(post(VERSION + BARS)
        .contentType(APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(BAR_DTO_3)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(3)))
        .andExpect(jsonPath("$.name", is("Bar3")))
        .andExpect(jsonPath("$.coordinates", nullValue()));

    verify(barService, times(1)).save(any(Bar.class));
  }

  @Test
  public void removeBar() throws Exception {
    Long barId = BAR_1.getId();

    doNothing().when(barService).deleteById(barId);

    mockMvc.perform(delete(VERSION + BARS + "{id}", barId))
        .andExpect(status().isOk());

    verify(barService, times(1)).deleteById(barId);
  }
=======
package com.mathieuaime.happyhourfinder.ws.bar;

import static com.mathieuaime.happyhourfinder.ws.common.constant.Paths.Bar.BARS;
import static com.mathieuaime.happyhourfinder.ws.common.constant.Paths.STATUS;
import static com.mathieuaime.happyhourfinder.ws.common.constant.Paths.VERSION;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mathieuaime.happyhourfinder.api.bar.BarDto;
import com.mathieuaime.happyhourfinder.api.bar.HappyHourDto;
import com.mathieuaime.happyhourfinder.mapper.bar.BarMapper;
import com.mathieuaime.happyhourfinder.model.bar.Bar;
import com.mathieuaime.happyhourfinder.model.bar.HappyHour;
import com.mathieuaime.happyhourfinder.service.bar.BarService;
import com.mathieuaime.happyhourfinder.ws.bar.BarControllerTest.Config;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(BarController.class)
@Import(Config.class)
public class BarControllerTest {

  @Configuration
  static class Config {

    @Bean
    public BarService barService() {
      return mock(BarService.class);
    }
  }


  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BarService barService;

  @MockBean
  private BarMapper barMapper;

  private static final GeometryFactory geometryFactory =
      new GeometryFactory(new PrecisionModel(), 26910);

  private ObjectMapper objectMapper = new ObjectMapper();

  private static final Point POINT_1 = geometryFactory.createPoint(new Coordinate(1, 2));
  private static final Point POINT_2 = geometryFactory.createPoint(new Coordinate(2, 3));

  private static final HappyHour HAPPY_HOUR =
      HappyHour.builder().begin(LocalTime.of(16, 0)).duration(Duration.ofHours(1)).build();

  private static final Bar BAR_1 =
      Bar.builder().id(1L).name("Bar1").coordinates(POINT_1).happyHour(HAPPY_HOUR).build();
  private static final Bar BAR_2 = Bar.builder().id(2L).name("Bar2").coordinates(POINT_2).build();
  private static final Bar BAR_3 = Bar.builder().id(3L).name("Bar3").build();

  private static final HappyHourDto HAPPY_HOUR_DTO =
      HappyHourDto.builder().begin("16:00").duration("PT1H").build();

  private static final BarDto BAR_DTO_1 =
      BarDto.builder().id(1L).name("Bar1").coordinates(POINT_1).happyHour(HAPPY_HOUR_DTO).build();
  private static final BarDto BAR_DTO_2 =
      BarDto.builder().id(2L).name("Bar2").coordinates(POINT_2).build();
  private static final BarDto BAR_DTO_3 = BarDto.builder().id(3L).name("Bar3").build();

  @Before
  public void setUp() {
    when(barMapper.convertToDto(BAR_1)).thenReturn(BAR_DTO_1);
    when(barMapper.convertToDto(BAR_2)).thenReturn(BAR_DTO_2);
    when(barMapper.convertToDto(BAR_3)).thenReturn(BAR_DTO_3);
    when(barMapper.convertToEntity(BAR_DTO_1)).thenReturn(BAR_1);
    when(barMapper.convertToEntity(BAR_DTO_2)).thenReturn(BAR_2);
    when(barMapper.convertToEntity(BAR_DTO_3)).thenReturn(BAR_3);
  }

  @After
  public void tearDown() {
    verifyNoMoreInteractions(barService);
  }

  @Test
  public void getStatus() throws Exception {
    mockMvc.perform(get(VERSION + BARS + STATUS)
        .contentType(MediaType.TEXT_PLAIN))
        .andExpect(status().isOk())
        .andExpect(content().string("working"));
  }

  @Test
  public void getBars() throws Exception {
    Page<Bar> paginableBar = new PageImpl<>(Arrays.asList(BAR_1, BAR_2));

    when(barService.findAll(any(Pageable.class))).thenReturn(paginableBar);

    mockMvc.perform(get(VERSION + BARS)
        .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content", hasSize(2)))
        .andExpect(jsonPath("$.content[0].id", is(1)))
        .andExpect(jsonPath("$.content[0].name", is("Bar1")))
        .andExpect(jsonPath("$.content[0].coordinates", is("POINT (1.0 2.0)")))
        .andExpect(jsonPath("$.content[1].id", is(2)))
        .andExpect(jsonPath("$.content[1].name", is("Bar2")))
        .andExpect(jsonPath("$.content[1].coordinates", is("POINT (2.0 3.0)")));

    verify(barService).findAll(any(Pageable.class));
  }

  @Test
  public void getBarById() throws Exception {
    Long barId = BAR_1.getId();

    when(barService.findById(barId)).thenReturn(Optional.of(BAR_1));

    mockMvc.perform(get(VERSION + BARS + "{id}", barId)
        .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.name", is("Bar1")))
        .andExpect(jsonPath("$.coordinates", is("POINT (1.0 2.0)")))
        .andExpect(jsonPath("$.happyHour.begin", is("16:00")))
        .andExpect(jsonPath("$.happyHour.duration", is("PT1H")));

    verify(barService, times(1)).findById(barId);
  }

  @Test
  public void getBarByIdNotFound() throws Exception {
    Long barId = BAR_1.getId();

    mockMvc.perform(get(VERSION + BARS + "{id}", 1L)
        .contentType(APPLICATION_JSON))
        .andExpect(status().isNotFound());

    verify(barService, times(1)).findById(barId);
  }

  @Test
  public void saveBar() throws Exception {

    when(barService.save(any(Bar.class))).then(invocationOnMock -> BAR_1);

    mockMvc.perform(post(VERSION + BARS)
        .contentType(APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(BAR_DTO_1)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.name", is("Bar1")))
        .andExpect(jsonPath("$.coordinates", is("POINT (1.0 2.0)")));

    verify(barService, times(1)).save(any(Bar.class));
    verifyNoMoreInteractions(barService);
  }

  @Test
  public void saveBarWithNullCoordinates() throws Exception {
    when(barService.save(any(Bar.class))).then(invocationOnMock -> BAR_3);

    mockMvc.perform(post(VERSION + BARS)
        .contentType(APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(BAR_DTO_3)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(3)))
        .andExpect(jsonPath("$.name", is("Bar3")))
        .andExpect(jsonPath("$.coordinates", nullValue()));

    verify(barService, times(1)).save(any(Bar.class));
  }

  @Test
  public void removeBar() throws Exception {
    Long barId = BAR_1.getId();

    doNothing().when(barService).deleteById(barId);

    mockMvc.perform(delete(VERSION + BARS + "{id}", barId))
        .andExpect(status().isOk());

    verify(barService, times(1)).deleteById(barId);
  }
>>>>>>> #11 re architecture:ws/src/test/java/com/mathieuaime/happyhourfinder/ws/bar/BarControllerTest.java
}