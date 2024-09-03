package com.mathieuaime.hhf.happyhour.web;

import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record HappyHourDto(
        Long id,
        @NotNull LocalTime startTime,
        @NotNull LocalTime endTime,
        @NotNull Long barId
) {

}
