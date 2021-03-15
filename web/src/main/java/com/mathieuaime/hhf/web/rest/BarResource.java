package com.mathieuaime.hhf.web.rest;

import com.mathieuaime.hhf.model.Bar;
import com.mathieuaime.hhf.model.request.BarSearch;
import com.mathieuaime.hhf.service.BarService;
import com.mathieuaime.hhf.web.dto.BarDto;
import com.mathieuaime.hhf.web.mapper.BarMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/bars")
public class BarResource {
    private final BarService barService;

    public BarResource(BarService barService) {
        this.barService = barService;
    }

    @GetMapping
    public List<BarDto> findAll(BarSearch search) {
        return barService.findAll(search).stream().map(BarMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BarDto findById(@PathVariable UUID id) {
        Bar bar = barService.findById(id);
        return BarMapper.toDto(bar);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BarDto create(@RequestBody @Valid BarDto barDto) {
        Bar bar = BarMapper.toModel(barDto);
        bar = barService.save(bar);
        return BarMapper.toDto(bar);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id) {
        barService.deleteById(id);
    }
}
