package com.mathieuaime.happyhourfinder.bar.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class BarDto {

  private Long id;

  @NonNull
  private String name;

  private String coordinates;

  private HappyHourDto happyHour;
}
