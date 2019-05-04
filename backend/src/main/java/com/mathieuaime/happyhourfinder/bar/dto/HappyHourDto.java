package com.mathieuaime.happyhourfinder.bar.dto;

import java.time.Duration;
import java.time.LocalTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HappyHourDto {

  private LocalTime begin;
  private Duration duration;
}
