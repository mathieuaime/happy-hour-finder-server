package com.mathieuaime.happyhourfinder.bar.model;

import java.time.Duration;
import java.time.LocalTime;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Embeddable
@Data
@Builder
public class HappyHour {

  private LocalTime begin;

  private Duration duration;
}
