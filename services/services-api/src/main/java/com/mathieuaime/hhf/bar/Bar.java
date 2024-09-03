package com.mathieuaime.hhf.bar;

import com.mathieuaime.hhf.geo.Coordinates;

import java.time.LocalTime;

public record Bar(
        Long id,
        String name,
        String address,
        LocalTime openTime,
        LocalTime closeTime,
        Coordinates position
) {

}
