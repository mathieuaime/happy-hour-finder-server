package com.mathieuaime.happyhourfinder.bar.service.impl;

import com.mathieuaime.happyhourfinder.bar.dao.BarDAO;
import com.mathieuaime.happyhourfinder.bar.model.Bar;
import com.mathieuaime.happyhourfinder.bar.service.BarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BarServiceImpl implements BarService {
    private final BarDAO barDAO;

    @Autowired
    public BarServiceImpl(BarDAO barDAO) {
        this.barDAO = barDAO;
    }

    @Override
    public Page<Bar> findAll(Pageable pageable) {
        return barDAO.findAll(pageable);
    }
}
