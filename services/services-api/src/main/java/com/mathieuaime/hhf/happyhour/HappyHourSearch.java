package com.mathieuaime.hhf.happyhour;

import com.mathieuaime.hhf.geo.PositionSearch;

import java.time.LocalTime;

public record HappyHourSearch(Long barId, LocalTime startTime, LocalTime endTime, PositionSearch position) {

}
