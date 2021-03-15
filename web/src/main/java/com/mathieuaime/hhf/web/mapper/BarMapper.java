package com.mathieuaime.hhf.web.mapper;

import com.mathieuaime.hhf.api.model.Bar;
import com.mathieuaime.hhf.web.dto.BarDto;

import java.util.List;
import java.util.stream.Collectors;

public final class BarMapper {
    private BarMapper() {
        throw new AssertionError();
    }

    public static List<BarDto> toDto(List<Bar> models) {
        return models.stream().map(BarMapper::toDto).collect(Collectors.toList());
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
}
