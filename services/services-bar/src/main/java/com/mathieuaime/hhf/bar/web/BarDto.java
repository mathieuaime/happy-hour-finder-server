package com.mathieuaime.hhf.bar.web;

import com.mathieuaime.hhf.geo.CoordinatesDto;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record BarDto(
        Long id,
        @NotNull String name,
        @NotNull String address,
        @NotNull LocalTime openTime,
        @NotNull LocalTime closeTime,
        @NotNull CoordinatesDto position
) {

}
