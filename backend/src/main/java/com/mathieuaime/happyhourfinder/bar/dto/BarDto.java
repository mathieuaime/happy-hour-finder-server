package com.mathieuaime.happyhourfinder.bar.dto;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class BarDto implements Serializable {

  private Long id;

  @NonNull
  private String name;

  private String coordinates;
}
