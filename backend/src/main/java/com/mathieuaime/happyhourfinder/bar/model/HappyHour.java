package com.mathieuaime.happyhourfinder.bar.model;

import java.time.Duration;
import java.time.LocalTime;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Embeddable
@Data
@AllArgsConstructor
public class HappyHour {

  private LocalTime begin;

  private Duration duration;
}
