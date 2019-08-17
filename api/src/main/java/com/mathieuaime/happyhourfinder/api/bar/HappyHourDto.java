package com.mathieuaime.happyhourfinder.api.bar;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HappyHourDto {

  private String begin;
  private String duration;
}
