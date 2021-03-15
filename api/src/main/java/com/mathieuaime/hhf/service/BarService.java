package com.mathieuaime.hhf.service;

import com.mathieuaime.hhf.model.Bar;
import com.mathieuaime.hhf.model.request.BarSearch;

import java.util.List;
import java.util.UUID;

public interface BarService {
    List<Bar> findAll(BarSearch search);

    Bar findById(UUID id);

    Bar save(Bar bar);

    void deleteById(UUID id);
}
