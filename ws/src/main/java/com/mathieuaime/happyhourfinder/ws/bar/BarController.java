package com.mathieuaime.happyhourfinder.ws.bar;

import com.mathieuaime.happyhourfinder.api.bar.BarDto;
import com.mathieuaime.happyhourfinder.api.trip.TripDto;
import com.mathieuaime.happyhourfinder.service.bar.BarFacade;
import com.mathieuaime.happyhourfinder.service.trip.TripFacade;
import com.mathieuaime.happyhourfinder.service.trip.request.GenerateTripRequest;
import com.mathieuaime.happyhourfinder.ws.bar.exception.BarNotFoundException;
import com.mathieuaime.happyhourfinder.ws.common.constant.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Paths.VERSION + Paths.Bar.BARS)
@CrossOrigin(origins = "http://localhost:4200")
public class BarController {

  private final BarFacade barFacade;
  private final TripFacade tripFacade;

  @Autowired
  public BarController(BarFacade barFacade, TripFacade tripFacade) {
    this.barFacade = barFacade;
    this.tripFacade = tripFacade;
  }

  @GetMapping(Paths.STATUS)
  public String status() {
    return "working";
  }

  @GetMapping
  public Page<BarDto> getBars(Pageable pageable) {
    return barFacade.findAll(pageable);
  }

  @GetMapping("/{id}")
  public BarDto getBar(@PathVariable Long id) {
    return barFacade.findById(id)
        .orElseThrow(BarNotFoundException::new);
  }

  @PostMapping
  public BarDto saveBar(@RequestBody BarDto barDto) {
    return barFacade.save(barDto);
  }

  @DeleteMapping("/{id}")
  public void deleteBar(@PathVariable Long id) {
    barFacade.deleteById(id);
  }

  @GetMapping(value = Paths.Bar.TRIPS, consumes = MediaType.APPLICATION_JSON_VALUE)
  public TripDto generateTrip(
      @RequestParam(value = "count", required = false, defaultValue = "1") int count,
      @RequestParam(value = "bars", required = false, defaultValue = "") List<Long> barIds) {
    GenerateTripRequest request = GenerateTripRequest.byCountAndMandatoryBars(count, barIds);
    return tripFacade.generate(request);
  }
}
