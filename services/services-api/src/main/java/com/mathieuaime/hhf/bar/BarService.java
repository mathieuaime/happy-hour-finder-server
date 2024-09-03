package com.mathieuaime.hhf.bar;

import java.util.List;

public interface BarService {
    List<Bar> findAll(BarSearch search);

    Bar findById(long id);

    Bar create(Bar bar);

    Bar update(long id, Bar bar);

    void deleteById(long id);
}
