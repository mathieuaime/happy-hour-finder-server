package com.mathieuaime.happyhourfinder.ws.bar;

import com.mathieuaime.happyhourfinder.api.bar.BarDto;
import com.mathieuaime.happyhourfinder.facade.bar.BarFacade;
import com.mathieuaime.happyhourfinder.ws.bar.exception.BarNotFoundException;
import com.mathieuaime.happyhourfinder.ws.common.constant.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

  @Autowired
  public BarController(BarFacade barFacade) {
    this.barFacade = barFacade;
  }

  @GetMapping(Paths.STATUS)
  public String status() {
    return "working";
  }

  @GetMapping
  public Page<BarDto> getBars(Pageable pageable) {
    return barFacade.findAll(pageable);
  }

  @GetMapping("/location")
  public List<BarDto> getBarsWithin(
      @RequestParam double lat, @RequestParam double lon, @RequestParam double distance) {
    return barFacade.findWithin(lat, lon, distance);
  }

  @GetMapping("/{id}")
  public BarDto getBar(@PathVariable Long id) {
    return barFacade.findById(id).orElseThrow(BarNotFoundException::new);
  }

  @PostMapping
  public BarDto saveBar(@RequestBody BarDto barDto) {
    return barFacade.save(barDto);
  }

  @DeleteMapping("/{id}")
  public void deleteBar(@PathVariable Long id) {
    barFacade.deleteById(id);
  }
}
