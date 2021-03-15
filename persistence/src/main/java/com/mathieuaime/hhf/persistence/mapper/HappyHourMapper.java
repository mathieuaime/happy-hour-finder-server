package com.mathieuaime.hhf.persistence.mapper;

import com.mathieuaime.hhf.model.HappyHour;
import com.mathieuaime.hhf.persistence.entity.HappyHourEntity;
import com.mathieuaime.hhf.persistence.projection.HappyHourProjection;

import java.util.List;
import java.util.stream.Collectors;

public final class HappyHourMapper {
    private HappyHourMapper() {
        throw new AssertionError();
    }

    public static List<HappyHour> toModel(List<HappyHourProjection> projections) {
        return projections.stream().map(HappyHourMapper::toModel).collect(Collectors.toList());
    }

    private static HappyHour toModel(HappyHourProjection projection) {
        return new HappyHour(projection.getId(), projection.getStart(), projection.getEnd());
    }

    public static HappyHour toModel(HappyHourEntity entity) {
        return new HappyHour(entity.getId(), entity.getStart(), entity.getEnd());
    }
}
