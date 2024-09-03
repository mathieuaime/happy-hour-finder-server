package com.mathieuaime.hhf.bar;

import com.mathieuaime.hhf.bar.persistence.BarDao;
import com.mathieuaime.hhf.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
class BarServiceImpl implements BarService {
    private final BarDao barRepository;

    public BarServiceImpl(BarDao barRepository) {
        this.barRepository = barRepository;
    }

    @Override
    public List<Bar> findAll(BarSearch search) {
        return barRepository.findAll(search);
    }

    @Override
    public Bar findById(long id) {
        return barRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public Bar create(Bar bar) {
        return barRepository.create(bar);
    }

    @Override
    @Transactional
    public Bar update(long id, Bar bar) {
        return barRepository.update(id, bar);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        barRepository.delete(id);
    }
}
