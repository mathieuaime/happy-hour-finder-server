package com.mathieuaime.hhf.bar.web;


import com.mathieuaime.hhf.bar.Bar;
import com.mathieuaime.hhf.geo.Coordinates;
import com.mathieuaime.hhf.geo.CoordinatesDto;
import com.mathieuaime.hhf.geo.CoordinatesMapper;

public final class BarMapper {
    private BarMapper() {
        throw new AssertionError();
    }

    public static BarDto toDto(Bar model) {
        CoordinatesDto position = CoordinatesMapper.toDto(model.position());
        return new BarDto(model.id(), model.name(), model.address(), model.openTime(), model.closeTime(), position);
    }

    public static Bar toModel(BarDto dto) {
        Coordinates position = CoordinatesMapper.toModel(dto.position());
        return new Bar(dto.id(), dto.name(), dto.address(), dto.openTime(), dto.closeTime(), position);
    }
}
