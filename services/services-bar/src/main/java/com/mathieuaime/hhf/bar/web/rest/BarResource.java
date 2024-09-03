package com.mathieuaime.hhf.bar.web.rest;

import com.mathieuaime.hhf.bar.Bar;
import com.mathieuaime.hhf.bar.BarSearch;
import com.mathieuaime.hhf.bar.BarService;
import com.mathieuaime.hhf.bar.web.BarDto;
import com.mathieuaime.hhf.bar.web.BarMapper;
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
@RequestMapping("/api/v1/bars")
class BarResource {
    private final BarService barService;

    public BarResource(BarService barService) {
        this.barService = barService;
    }

    @GetMapping
    List<BarDto> findAll(BarSearch search) {
        return barService.findAll(search).stream().map(BarMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    BarDto findById(@PathVariable long id) {
        Bar bar = barService.findById(id);
        return BarMapper.toDto(bar);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    BarDto create(@RequestBody @Valid BarDto barDto) {
        Bar bar = BarMapper.toModel(barDto);
        bar = barService.create(bar);
        return BarMapper.toDto(bar);
    }

    @PutMapping("/{id}")
    BarDto update(@PathVariable long id, @RequestBody @Valid BarDto barDto) {
        Bar bar = BarMapper.toModel(barDto);
        bar = barService.update(id, bar);
        return BarMapper.toDto(bar);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable long id) {
        barService.deleteById(id);
    }
}
