package com.mathieuaime.hhf.web.mapper;

import com.mathieuaime.hhf.api.model.HappyHour;
import com.mathieuaime.hhf.web.dto.HappyHourDto;

import java.util.List;
import java.util.stream.Collectors;

public final class HappyHourMapper {
    private HappyHourMapper() {
        throw new AssertionError();
    }

    public static List<HappyHourDto> toDto(List<HappyHour> models) {
        return models.stream().map(HappyHourMapper::toDto).collect(Collectors.toList());
    }

    public static HappyHourDto toDto(HappyHour model) {
        HappyHourDto dto = new HappyHourDto();
        dto.setId(model.getId());
        dto.setStart(model.getStart());
        dto.setEnd(model.getEnd());
        return dto;
    }
}
