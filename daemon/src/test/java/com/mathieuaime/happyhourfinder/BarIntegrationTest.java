package com.mathieuaime.happyhourfinder;

import static com.mathieuaime.happyhourfinder.ws.common.constant.Paths.Bar.BARS;
import static com.mathieuaime.happyhourfinder.ws.common.constant.Paths.VERSION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mathieuaime.happyhourfinder.api.bar.BarDto;
import com.mathieuaime.happyhourfinder.api.bar.HappyHourDto;
import com.mathieuaime.happyhourfinder.model.bar.Bar;
import com.mathieuaime.happyhourfinder.model.bar.HappyHour;
import com.mathieuaime.happyhourfinder.repository.bar.BarDao;
import com.mathieuaime.happyhourfinder.type.IntegrationTest;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Optional;
import java.util.stream.IntStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@Category(IntegrationTest.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HappyHourFinderApplication.class)
@AutoConfigureMockMvc
public class BarIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private BarDao barDao;

  private static final GeoJsonPoint POINT = new GeoJsonPoint(1, 2);

  private static final HappyHour HAPPY_HOUR =
      new HappyHour().begin(LocalTime.of(12, 0)).duration(Duration.ofHours(1));

  private static final HappyHourDto HAPPY_HOUR_DTO =
      new HappyHourDto().begin("12:00").duration("PT1H");

  private static final BarDto FULL_BAR =
      new BarDto().name("newFullBar").coordinates(POINT).happyHour(HAPPY_HOUR_DTO);
  private static final BarDto BASIC_BAR = new BarDto().name("newBasicBar");

  @Before
  public void setUp() {
    IntStream.range(0, 20).mapToObj(this::generateBar).forEach(barDao::save);
  }

  private Bar generateBar(int i) {
    return new Bar().uuid("uuid-" + i).name("Bar" + i).coordinates(new GeoJsonPoint(i, i + 1))
        .happyHour(
            new HappyHour().begin(LocalTime.of((10 + i) % 24, 0)).duration(Duration.ofHours(2)));
  }

  @After
  public void tearDown() {
    barDao.deleteAll();
  }

  @Test
  public void getBars() throws Exception {
    mockMvc.perform(get(VERSION + BARS)
        .contentType(APPLICATION_JSON)
        .param("size", "5")
        .param("page", "2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content", hasSize(5)))
        .andExpect(jsonPath("$.content[0].name", is("Bar10")));
  }

  @Test
  public void getBarByUuid() throws Exception {
    String barUuid = "uuid-1";

    mockMvc.perform(get(VERSION + BARS + "{uuid}", barUuid)
        .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(is(barUuid), String.class))
        .andExpect(jsonPath("$.name", is("Bar1")))
        .andExpect(jsonPath("$.coordinates", is("POINT (1.0 2.0)")))
        .andExpect(jsonPath("$.happyHour.begin", is("11:00")))
        .andExpect(jsonPath("$.happyHour.duration", is("PT2H")));
  }

  @Test
  public void getBarByIdNotFound() throws Exception {
    mockMvc.perform(get(VERSION + BARS + "{uuid}", "1234")
        .contentType(APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void saveBasicBar() throws Exception {
    MockHttpServletResponse response = mockMvc.perform(post(VERSION + BARS)
        .contentType(APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(BASIC_BAR)))
        .andExpect(status().isOk()).andReturn().getResponse();

    BarDto barCreated = objectMapper.readValue(response.getContentAsString(), BarDto.class);

    Optional<Bar> newBar = barDao.findById(barCreated.getUuid());
    assertThat(newBar).map(Bar::getUuid).isNotNull();
    assertThat(newBar).map(Bar::getName).contains(BASIC_BAR.getName());
    assertThat(newBar).map(Bar::getCoordinates).isEmpty();
    assertThat(newBar).map(Bar::getHappyHour).isEmpty();
  }

  @Test
  public void saveFullBar() throws Exception {
    MockHttpServletResponse response = mockMvc.perform(post(VERSION + BARS)
        .contentType(APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(FULL_BAR)))
        .andExpect(status().isOk()).andReturn().getResponse();

    BarDto barCreated = objectMapper.readValue(response.getContentAsString(), BarDto.class);

    Optional<Bar> newBar = barDao.findById(barCreated.getUuid());
    assertThat(newBar).map(Bar::getUuid).isNotNull();
    assertThat(newBar).map(Bar::getName).contains(FULL_BAR.getName());
    assertThat(newBar).map(Bar::getCoordinates).contains(POINT);
    assertThat(newBar).map(Bar::getHappyHour).contains(HAPPY_HOUR);
  }

  @Test
  public void removeBar() throws Exception {
    String uuid = "uuid-1";

    mockMvc.perform(delete(VERSION + BARS + "{uuid}", uuid))
        .andExpect(status().isOk());

    Optional<Bar> bar = barDao.findById(uuid);
    assertThat(bar).isEmpty();
  }
}