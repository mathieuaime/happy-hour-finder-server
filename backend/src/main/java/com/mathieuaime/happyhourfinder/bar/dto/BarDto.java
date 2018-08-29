package com.mathieuaime.happyhourfinder.bar.dto;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class BarDto implements Serializable {

  private Long id;

  @NonNull
  private String name;
}
