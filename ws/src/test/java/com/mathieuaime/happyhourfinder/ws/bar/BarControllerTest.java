package com.mathieuaime.happyhourfinder.ws.bar;

import static com.mathieuaime.happyhourfinder.ws.common.constant.Paths.Bar.BARS;
import static com.mathieuaime.happyhourfinder.ws.common.constant.Paths.STATUS;
import static com.mathieuaime.happyhourfinder.ws.common.constant.Paths.VERSION;
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
import com.mathieuaime.happyhourfinder.api.bar.BarDto;
import com.mathieuaime.happyhourfinder.api.bar.HappyHourDto;
import com.mathieuaime.happyhourfinder.facade.bar.BarFacade;
import com.mathieuaime.happyhourfinder.type.IntegrationTest;
import java.util.Arrays;
import java.util.Optional;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(BarController.class)
@ContextConfiguration(classes = Config.class)
@Category(IntegrationTest.class)
public class BarControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BarFacade barFacade;

  private ObjectMapper objectMapper = new ObjectMapper();

  private static final GeoJsonPoint POINT_1 = new GeoJsonPoint(1, 2);
  private static final GeoJsonPoint POINT_2 = new GeoJsonPoint(2, 3);

  private static final HappyHourDto HAPPY_HOUR_DTO =
      new HappyHourDto().begin("16:00").duration("PT1H");

  private static final BarDto BAR_DTO_1 =
      new BarDto().id(1L).name("Bar1").coordinates(POINT_1).happyHour(HAPPY_HOUR_DTO);
  private static final BarDto BAR_DTO_2 = new BarDto().id(2L).name("Bar2").coordinates(POINT_2);
  private static final BarDto BAR_DTO_3 = new BarDto().id(3L).name("Bar3");

  @After
  public void tearDown() {
    verifyNoMoreInteractions(barFacade);
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
    Page<BarDto> paginableBar = new PageImpl<>(Arrays.asList(BAR_DTO_1, BAR_DTO_2));

    when(barFacade.findAll(any(Pageable.class))).thenReturn(paginableBar);

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

    verify(barFacade, times(1)).findAll(any(Pageable.class));
  }

  @Test
  public void getBarById() throws Exception {
    Long barId = BAR_DTO_1.getId();

    when(barFacade.findById(barId)).thenReturn(Optional.of(BAR_DTO_1));

    mockMvc.perform(get(VERSION + BARS + "{id}", barId)
        .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.name", is("Bar1")))
        .andExpect(jsonPath("$.coordinates", is("POINT (1.0 2.0)")))
        .andExpect(jsonPath("$.happyHour.begin", is("16:00")))
        .andExpect(jsonPath("$.happyHour.duration", is("PT1H")));

    verify(barFacade, times(1)).findById(barId);
  }

  @Test
  public void getBarByIdNotFound() throws Exception {
    Long barId = BAR_DTO_1.getId();

    mockMvc.perform(get(VERSION + BARS + "{id}", 1L)
        .contentType(APPLICATION_JSON))
        .andExpect(status().isNotFound());

    verify(barFacade, times(1)).findById(barId);
  }

  @Test
  public void saveBar() throws Exception {

    when(barFacade.save(any(BarDto.class))).then(invocationOnMock -> BAR_DTO_1);

    mockMvc.perform(post(VERSION + BARS)
        .contentType(APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(BAR_DTO_1)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.name", is("Bar1")))
        .andExpect(jsonPath("$.coordinates", is("POINT (1.0 2.0)")));

    verify(barFacade, times(1)).save(any(BarDto.class));
    verifyNoMoreInteractions(barFacade);
  }

  @Test
  public void saveBarWithNullCoordinates() throws Exception {
    when(barFacade.save(any(BarDto.class))).then(invocationOnMock -> BAR_DTO_3);

    mockMvc.perform(post(VERSION + BARS)
        .contentType(APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(BAR_DTO_3)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(3)))
        .andExpect(jsonPath("$.name", is("Bar3")))
        .andExpect(jsonPath("$.coordinates", nullValue()));

    verify(barFacade, times(1)).save(any(BarDto.class));
  }

  @Test
  public void removeBar() throws Exception {
    Long barId = BAR_DTO_1.getId();

    doNothing().when(barFacade).deleteById(barId);

    mockMvc.perform(delete(VERSION + BARS + "{id}", barId))
        .andExpect(status().isOk());

    verify(barFacade, times(1)).deleteById(barId);
  }
}