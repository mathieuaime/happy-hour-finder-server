package com.mathieuaime.happyhourfinder.bar.service;

import com.mathieuaime.happyhourfinder.bar.model.Bar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BarService {
    Page<Bar> findAll(Pageable pageable);
}
