package com.mathieuaime.happyhourfinder.ws.trip;

import com.mathieuaime.happyhourfinder.api.trip.TripDto;
import com.mathieuaime.happyhourfinder.facade.trip.TripFacade;
import com.mathieuaime.happyhourfinder.facade.trip.request.GenerateTripRequest;
import com.mathieuaime.happyhourfinder.ws.common.constant.Paths;
import com.mathieuaime.happyhourfinder.ws.common.constant.Paths.Trip;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Paths.VERSION + Trip.TRIPS)
@CrossOrigin(origins = "http://localhost:4200")
public class TripController {

  private final TripFacade tripFacade;

  @Autowired
  public TripController(TripFacade tripFacade) {
    this.tripFacade = tripFacade;
  }

  @GetMapping(Paths.STATUS)
  public String status() {
    return "working";
  }

  @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public TripDto generateTrip(
      @RequestParam(value = "count", required = false, defaultValue = "4") int count,
      @RequestParam(value = "bars", required = false, defaultValue = "") List<String> uuids) {
    GenerateTripRequest request = GenerateTripRequest.byCountAndMandatoryBars(count, uuids);
    return tripFacade.generate(request);
  }
}
