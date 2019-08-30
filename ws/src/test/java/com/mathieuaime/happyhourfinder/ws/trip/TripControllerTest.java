package com.mathieuaime.happyhourfinder.ws.trip;

import static com.mathieuaime.happyhourfinder.ws.common.constant.Paths.STATUS;
import static com.mathieuaime.happyhourfinder.ws.common.constant.Paths.Trip.TRIPS;
import static com.mathieuaime.happyhourfinder.ws.common.constant.Paths.VERSION;
import static org.hamcrest.Matchers.hasSize;
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

  private static final BarDto BAR_DTO = BarDto.builder().id(1L).name("Bar").build();

  private static final TripDto TRIP_DTO = TripDto.create(Collections.singletonList(BAR_DTO));

  @Test
  public void getStatus() throws Exception {
    mockMvc.perform(get(VERSION + TRIPS + STATUS)
        .contentType(MediaType.TEXT_PLAIN))
        .andExpect(status().isOk())
        .andExpect(content().string("working"));
  }

  @Test
  public void generateTripWithDefaultValue() throws Exception {
    GenerateTripRequest request = GenerateTripRequest.byCount(4);
    when(tripFacade.generate(request)).thenReturn(TRIP_DTO);

    mockMvc.perform(get(VERSION + TRIPS)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.bars", hasSize(1)));

    verify(tripFacade).generate(request);
  }

  @Test
  public void generateTripWithCount() throws Exception {
    GenerateTripRequest request = GenerateTripRequest.byCount(1);
    when(tripFacade.generate(request)).thenReturn(TRIP_DTO);

    mockMvc.perform(get(VERSION + TRIPS)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .param("count", "1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.bars", hasSize(1)));

    verify(tripFacade).generate(request);
  }

  @Test
  public void generateTripWithCountAndMandatoryBars() throws Exception {
    GenerateTripRequest request =
        GenerateTripRequest.byCountAndMandatoryBars(1, Arrays.asList(1L, 2L));
    when(tripFacade.generate(request)).thenReturn(TRIP_DTO);

    mockMvc.perform(get(VERSION + TRIPS)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .param("count", "1")
        .param("bars", "1")
        .param("bars", "2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.bars", hasSize(1)));

    verify(tripFacade).generate(request);
  }
}
