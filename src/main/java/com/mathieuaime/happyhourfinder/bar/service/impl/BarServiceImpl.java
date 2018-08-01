package com.mathieuaime.happyhourfinder.bar.service.impl;

import com.mathieuaime.happyhourfinder.bar.dao.BarDAO;
import com.mathieuaime.happyhourfinder.bar.model.Bar;
import com.mathieuaime.happyhourfinder.bar.service.BarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Service
@CrossOrigin(origins = "http://localhost:4200")
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

    @Override
    public Optional<Bar> findById(Long id) {
        return barDAO.findById(id);
    }

    @Override
    public Bar save(Bar bar) {
        return barDAO.save(bar);
    }

    @Override
    public Bar update(Long barId, Bar bar) {
        bar.setId(barId);
        return barDAO.save(bar);
    }

    @Override
    public void deleteById(Long barId) {
        barDAO.deleteById(barId);
    }
}
