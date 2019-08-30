package com.mathieuaime.happyhourfinder.model.bar;

import java.time.Duration;
import java.time.LocalTime;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
public class HappyHour {

  private LocalTime begin;

  private Duration duration;
}
