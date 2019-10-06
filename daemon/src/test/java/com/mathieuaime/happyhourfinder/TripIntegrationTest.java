package com.mathieuaime.happyhourfinder;

import static com.mathieuaime.happyhourfinder.ws.common.constant.Paths.Trip.TRIPS;
import static com.mathieuaime.happyhourfinder.ws.common.constant.Paths.VERSION;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mathieuaime.happyhourfinder.model.bar.Bar;
import com.mathieuaime.happyhourfinder.model.bar.HappyHour;
import com.mathieuaime.happyhourfinder.repository.bar.BarDao;
import com.mathieuaime.happyhourfinder.type.IntegrationTest;
import java.time.Duration;
import java.time.LocalTime;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@Category(IntegrationTest.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HappyHourFinderApplication.class)
@AutoConfigureMockMvc
public class TripIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private BarDao barDao;

  @Before
  public void setUp() {
    IntStream.range(1, 10).mapToObj(this::generateBar).forEach(barDao::save);
  }

  private Bar generateBar(int i) {
    return new Bar().name("Bar" + i).coordinates(new GeoJsonPoint(i, i + 1)).happyHour(
        new HappyHour().begin(LocalTime.of((10 + i) % 24, 0)).duration(Duration.ofHours(2)));
  }

  @After
  public void tearDown() {
    barDao.deleteAll();
  }

  @Test
  public void generateTripWithDefaultValue() throws Exception {
    mockMvc.perform(get(VERSION + TRIPS)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.bars", hasSize(4)));
  }

  @Test
  public void generateTripWithCount() throws Exception {
    mockMvc.perform(get(VERSION + TRIPS)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .param("count", "1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.bars", hasSize(1)));
  }

  @Test
  public void generateTripWithCountAndMandatoryBars() throws Exception {
    mockMvc.perform(get(VERSION + TRIPS)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .param("count", "5")
        .param("bars", "1")
        .param("bars", "2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.bars", hasSize(5)));
  }
}