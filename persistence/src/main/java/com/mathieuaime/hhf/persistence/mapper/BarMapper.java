package com.mathieuaime.hhf.persistence.mapper;

import com.mathieuaime.hhf.api.model.Bar;
import com.mathieuaime.hhf.persistence.model.BarEntity;
import com.mathieuaime.hhf.persistence.model.HappyHourProjection;

public final class BarMapper {
    private BarMapper() {
        throw new AssertionError();
    }

    public static Bar toModel(BarEntity entity) {
        return new Bar(entity.getId(), entity.getName(), entity.getOpen(), entity.getClose());
    }

    public static Bar toModel(HappyHourProjection projection) {
        throw new UnsupportedOperationException();
    }
}
