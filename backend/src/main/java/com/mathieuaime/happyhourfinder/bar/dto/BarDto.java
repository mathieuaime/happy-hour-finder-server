package com.mathieuaime.happyhourfinder.bar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@ApiModel("Bar")
@Data
@Builder
public class BarDto implements Serializable {

  private Long id;

  @NonNull
  private String name;

  @ApiModelProperty(example = "POINT (1.0 1.0)")
  private String coordinates;
}
