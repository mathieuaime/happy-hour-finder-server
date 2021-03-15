package com.mathieuaime.hhf.repository;

import com.mathieuaime.hhf.api.model.Bar;

import java.util.List;

public interface BarRepository {
    List<Bar> findAll();
}
