package com.mathieuaime.happyhourfinder.ws.trip;

import static com.mathieuaime.happyhourfinder.ws.common.constant.Paths.STATUS;
import static com.mathieuaime.happyhourfinder.ws.common.constant.Paths.Trip.TRIPS;
import static com.mathieuaime.happyhourfinder.ws.common.constant.Paths.VERSION;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mathieuaime.happyhourfinder.api.bar.BarDto;
import com.mathieuaime.happyhourfinder.api.trip.TripDto;
import com.mathieuaime.happyhourfinder.facade.trip.TripFacade;
import com.mathieuaime.happyhourfinder.facade.trip.request.GenerateTripRequest;
import com.mathieuaime.happyhourfinder.type.IntegrationTest;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(TripController.class)
@ContextConfiguration(classes = Config.class)
@Category(IntegrationTest.class)
public class TripControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TripFacade tripFacade;

  private static final BarDto BAR_DTO = new BarDto();

  private static final TripDto TRIP_DTO = new TripDto().bars(Collections.singletonList(BAR_DTO));

  @Before
  public void setUp() throws Exception {
    when(tripFacade.generate(any(GenerateTripRequest.class))).thenReturn(TRIP_DTO);
  }

  @Test
  public void getStatus() throws Exception {
    mockMvc.perform(get(VERSION + TRIPS + STATUS)
        .contentType(MediaType.TEXT_PLAIN))
        .andExpect(status().isOk())
        .andExpect(content().string("working"));
  }

  @Test
  public void generateTripWithDefaultValue() throws Exception {
    mockMvc.perform(get(VERSION + TRIPS)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.bars", hasSize(1)));

    GenerateTripRequest request = GenerateTripRequest.byCount(4);
    verify(tripFacade).generate(request);
  }

  @Test
  public void generateTripWithCount() throws Exception {
    mockMvc.perform(get(VERSION + TRIPS)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .param("count", "1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.bars", hasSize(1)));

    GenerateTripRequest request = GenerateTripRequest.byCount(1);
    verify(tripFacade).generate(request);
  }

  @Test
  public void generateTripWithCountAndMandatoryBars() throws Exception {
    mockMvc.perform(get(VERSION + TRIPS)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .param("count", "1")
        .param("bars", "uuid-1")
        .param("bars", "uuid-2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.bars", hasSize(1)));

    GenerateTripRequest request =
        GenerateTripRequest.byCountAndMandatoryBars(1, Arrays.asList("uuid-1", "uuid-2"));
    verify(tripFacade).generate(request);
  }
}
