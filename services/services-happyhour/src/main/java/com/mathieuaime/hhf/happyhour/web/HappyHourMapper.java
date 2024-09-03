package com.mathieuaime.hhf.happyhour.web;

import com.mathieuaime.hhf.happyhour.HappyHour;

public final class HappyHourMapper {
    private HappyHourMapper() {
        throw new AssertionError();
    }

    public static HappyHourDto toDto(HappyHour model) {
        return new HappyHourDto(model.id(), model.startTime(), model.endTime(), model.barId());
    }

    public static HappyHour toModel(HappyHourDto dto) {
        return new HappyHour(dto.id(), dto.startTime(), dto.endTime(), dto.barId());
    }
}
