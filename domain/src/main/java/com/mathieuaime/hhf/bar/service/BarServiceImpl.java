package com.mathieuaime.hhf.bar.service;

import com.mathieuaime.hhf.model.Bar;
import com.mathieuaime.hhf.model.request.BarSearch;
import com.mathieuaime.hhf.service.BarService;
import com.mathieuaime.hhf.repository.BarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BarServiceImpl implements BarService {
    private final BarRepository barRepository;

    public BarServiceImpl(BarRepository barRepository) {
        this.barRepository = barRepository;
    }

    @Override
    public List<Bar> findAll(BarSearch search) {
        return barRepository.findAll(search);
    }

    @Override
    public Bar findById(UUID id) {
        return barRepository.findById(id);
    }

    @Override
    public Bar save(Bar bar) {
        return barRepository.save(bar);
    }

    @Override
    public void deleteById(UUID id) {
        barRepository.deleteById(id);
    }
}
