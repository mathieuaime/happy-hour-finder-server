package com.mathieuaime.hhf.web.mapper;

import com.mathieuaime.hhf.model.Bar;
import com.mathieuaime.hhf.web.dto.BarDto;

public final class BarMapper {
    private BarMapper() {
        throw new AssertionError();
    }

    public static BarDto toDto(Bar model) {
        BarDto dto = new BarDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setOpen(model.getOpen());
        dto.setClose(model.getClose());
        dto.setHappyHours(HappyHourMapper.toDto(model.getHappyHours()));
        return dto;
    }

    public static Bar toModel(BarDto dto) {
        return new Bar(dto.getId(), dto.getName(), dto.getOpen(), dto.getClose());
    }
}
