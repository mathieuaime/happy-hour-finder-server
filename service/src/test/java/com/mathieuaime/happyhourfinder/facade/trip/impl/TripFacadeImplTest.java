package com.mathieuaime.happyhourfinder.facade.trip.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.mathieuaime.happyhourfinder.api.trip.TripDto;
import com.mathieuaime.happyhourfinder.mapper.trip.TripMapper;
import com.mathieuaime.happyhourfinder.model.trip.Trip;
import com.mathieuaime.happyhourfinder.service.trip.TripFacade;
import com.mathieuaime.happyhourfinder.service.trip.TripService;
import com.mathieuaime.happyhourfinder.service.trip.request.GenerateTripRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TripFacadeImplTest {

  @Mock
  private TripService tripService;
  @Mock
  private TripMapper tripMapper;

  private TripFacade mockTripFacade;

  @Mock
  private Trip trip;
  @Mock
  private TripDto tripDto;

  @Before
  public void setUp() throws Exception {
    mockTripFacade = new TripFacadeImpl(tripService, tripMapper);
    when(tripMapper.convertToDto(this.trip)).thenReturn(this.tripDto);
  }

  @After
  public void tearDown() throws Exception {
    verifyNoMoreInteractions(tripService, tripMapper);
  }

  @Test
  public void generate() throws Exception {
    GenerateTripRequest request = GenerateTripRequest.byCount(1);
    when(tripService.generate(request)).thenReturn(this.trip);

    TripDto tripDto = mockTripFacade.generate(request);

    assertThat(tripDto).isEqualTo(this.tripDto);
    verify(tripService).generate(request);
    verify(tripMapper).convertToDto(this.trip);
  }
}
