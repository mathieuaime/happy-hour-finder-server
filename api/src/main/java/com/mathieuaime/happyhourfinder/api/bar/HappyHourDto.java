package com.mathieuaime.happyhourfinder.api.bar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
public class HappyHourDto {

  private String begin;
  private String duration;
}
