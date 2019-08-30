package com.mathieuaime.happyhourfinder;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class HappyHourFinderApplication {

  public static void main(String[] args) {
    SpringApplication.run(HappyHourFinderApplication.class, args);
  }
}
