package com.mathieuaime.hhf.bar.service;

import com.mathieuaime.hhf.api.model.Bar;
import com.mathieuaime.hhf.api.service.BarService;
import com.mathieuaime.hhf.repository.BarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarServiceImpl implements BarService {
    private final BarRepository barRepository;

    public BarServiceImpl(BarRepository barRepository) {
        this.barRepository = barRepository;
    }

    @Override
    public List<Bar> findAll() {
        return barRepository.findAll();
    }
}
