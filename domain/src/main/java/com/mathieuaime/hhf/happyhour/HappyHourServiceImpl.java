package com.mathieuaime.hhf.happyhour;

import com.mathieuaime.hhf.model.HappyHour;
import com.mathieuaime.hhf.model.request.HappyHourSearch;
import com.mathieuaime.hhf.repository.HappyHourRepository;
import com.mathieuaime.hhf.service.HappyHourService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HappyHourServiceImpl implements HappyHourService {
    private final HappyHourRepository happyHourRepository;

    public HappyHourServiceImpl(HappyHourRepository happyHourRepository) {
        this.happyHourRepository = happyHourRepository;
    }

    @Override
    public List<HappyHour> findAll(HappyHourSearch search) {
        return happyHourRepository.findAll(search);
    }

    @Override
    public HappyHour save(HappyHour happyHour) {
        return happyHourRepository.save(happyHour);
    }

    @Override
    public void deleteById(UUID id) {
        happyHourRepository.deleteById(id);
    }
}
