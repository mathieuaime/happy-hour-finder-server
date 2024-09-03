package com.mathieuaime.hhf.happyhour;

import com.mathieuaime.hhf.exception.ResourceNotFoundException;
import com.mathieuaime.hhf.happyhour.persistence.HappyHourDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
class HappyHourServiceImpl implements HappyHourService {
    private final HappyHourDao happyhourRepository;

    public HappyHourServiceImpl(HappyHourDao happyhourRepository) {
        this.happyhourRepository = happyhourRepository;
    }

    @Override
    public List<HappyHour> findAll(HappyHourSearch search) {
        return happyhourRepository.findAll(search);
    }

    @Override
    public HappyHour findById(long id) {
        return happyhourRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public HappyHour create(HappyHour happyhour) {
        return happyhourRepository.create(happyhour);
    }

    @Override
    @Transactional
    public HappyHour update(long id, HappyHour happyhour) {
        return happyhourRepository.update(id, happyhour);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        happyhourRepository.delete(id);
    }
}
