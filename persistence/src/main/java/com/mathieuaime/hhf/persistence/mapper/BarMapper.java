package com.mathieuaime.hhf.persistence.mapper;

import com.mathieuaime.hhf.model.Bar;
import com.mathieuaime.hhf.model.HappyHour;
import com.mathieuaime.hhf.persistence.entity.BarEntity;
import com.mathieuaime.hhf.persistence.projection.HappyHourProjection;

import java.util.List;
import java.util.Optional;

public final class BarMapper {
    private BarMapper() {
        throw new AssertionError();
    }

    public static Bar toModel(BarEntity entity) {
        return new Bar(entity.getId(), entity.getName(), entity.getOpen(), entity.getClose());
    }

    public static Optional<Bar> toModel(List<HappyHourProjection> projections) {
        return projections.stream()
                .findFirst()
                .map(projection -> getBar(projection, HappyHourMapper.toModel(projections)));
    }

    private static Bar getBar(HappyHourProjection projection, List<HappyHour> happyHours) {
        return new Bar(projection.getBarId(), projection.getBarName(), projection.getBarOpen(), projection.getBarClose(), happyHours);
    }
}
