package com.mathieuaime.hhf.repository;

import com.mathieuaime.hhf.model.HappyHour;
import com.mathieuaime.hhf.model.request.HappyHourSearch;

import java.util.List;
import java.util.UUID;

public interface HappyHourRepository {
    List<HappyHour> findAll(HappyHourSearch search);

    HappyHour save(HappyHour happyHour);

    void deleteById(UUID id);
}
