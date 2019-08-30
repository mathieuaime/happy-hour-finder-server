package com.mathieuaime.happyhourfinder.api.trip;

import com.mathieuaime.happyhourfinder.api.bar.BarDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
public class TripDto {

  private List<BarDto> bars;
}
