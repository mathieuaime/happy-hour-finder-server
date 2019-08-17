package com.mathieuaime.happyhourfinder;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@Log
public class HappyHourFinderApplication {

  public static void main(String[] args) {
    SpringApplication.run(HappyHourFinderApplication.class, args);
  }
}
