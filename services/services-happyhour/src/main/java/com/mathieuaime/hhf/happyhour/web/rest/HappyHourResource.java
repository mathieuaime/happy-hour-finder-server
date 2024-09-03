package com.mathieuaime.hhf.happyhour.web.rest;

import com.mathieuaime.hhf.exception.BadRequestException;
import com.mathieuaime.hhf.happyhour.HappyHour;
import com.mathieuaime.hhf.happyhour.HappyHourSearch;
import com.mathieuaime.hhf.happyhour.HappyHourService;
import com.mathieuaime.hhf.happyhour.web.HappyHourDto;
import com.mathieuaime.hhf.happyhour.web.HappyHourMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/happy-hours")
class HappyHourResource {
    private final HappyHourService happyHourService;

    public HappyHourResource(HappyHourService happyHourService) {
        this.happyHourService = happyHourService;
    }

    @GetMapping
    List<HappyHourDto> findAll(HappyHourSearch search) {
        return happyHourService.findAll(search).stream().map(HappyHourMapper::toDto).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    HappyHourDto create(@RequestBody @Valid HappyHourDto happyHourDto) {
        HappyHour happyHour = HappyHourMapper.toModel(happyHourDto);
        happyHour = happyHourService.create(happyHour);
        return HappyHourMapper.toDto(happyHour);
    }

    @PutMapping("/{id}")
    HappyHourDto update(@PathVariable long id, @RequestBody @Valid HappyHourDto happyHourDto) {
        HappyHour happyHour = HappyHourMapper.toModel(happyHourDto);
        happyHour = happyHourService.update(id, happyHour);
        return HappyHourMapper.toDto(happyHour);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable long id) {
        happyHourService.deleteById(id);
    }
}
