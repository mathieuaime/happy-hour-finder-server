package com.mathieuaime.happyhourfinder.bar.controller;

import com.mathieuaime.happyhourfinder.common.constants.Paths;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Paths.VERSION + Paths.CONFIG)
public class ConfigController {

  @Value("${google.maps.api.key}")
  private String mapsAPIKey;

  @GetMapping(Paths.MAPS_API_KEY)
  public String getMapsAPIKey() {
    return mapsAPIKey;
  }
}
