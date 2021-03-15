package com.mathieuaime.hhf.web.rest;

import com.mathieuaime.hhf.api.service.BarService;
import com.mathieuaime.hhf.web.dto.BarDto;
import com.mathieuaime.hhf.web.mapper.BarMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/bars")
public class BarResource {
    private final BarService barService;

    public BarResource(BarService barService) {
        this.barService = barService;
    }

    @GetMapping
    public List<BarDto> findAll() {
        return barService.findAll().stream().map(BarMapper::toDto).collect(Collectors.toList());
    }
}
