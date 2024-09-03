package com.mathieuaime.hhf.bar;

import com.mathieuaime.hhf.geo.PositionSearch;

import java.time.LocalTime;

public record BarSearch(String name, LocalTime openTime, LocalTime closeTime, PositionSearch position) {
    private static final BarSearch ALL = new BarSearch(null, null, null, null);

    public static BarSearch all() {
        return ALL;
    }
}
