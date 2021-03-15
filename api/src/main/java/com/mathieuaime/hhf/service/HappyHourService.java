package com.mathieuaime.hhf.service;

import com.mathieuaime.hhf.model.HappyHour;
import com.mathieuaime.hhf.model.request.HappyHourSearch;

import java.util.List;
import java.util.UUID;

public interface HappyHourService {
    List<HappyHour> findAll(HappyHourSearch search);

    HappyHour save(HappyHour happyHour);

    void deleteById(UUID id);
}
