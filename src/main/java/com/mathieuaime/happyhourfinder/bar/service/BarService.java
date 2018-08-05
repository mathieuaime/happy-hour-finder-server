package com.mathieuaime.happyhourfinder.bar.service;

import com.mathieuaime.happyhourfinder.bar.model.Bar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface BarService {
    Page<Bar> findAll(Pageable pageable);

    Optional<Bar> findById(Long id);

    Bar save(Bar bar);

    void deleteById(Long barId);
}
