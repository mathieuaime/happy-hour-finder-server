package com.mathieuaime.hhf.happyhour;

import java.time.LocalTime;

public record HappyHour(Long id, LocalTime startTime, LocalTime endTime, long barId) {

}
