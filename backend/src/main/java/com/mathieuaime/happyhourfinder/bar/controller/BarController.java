package com.mathieuaime.happyhourfinder.bar.controller;

import com.mathieuaime.happyhourfinder.bar.dto.BarDto;
import com.mathieuaime.happyhourfinder.bar.exception.BarNotFoundException;
import com.mathieuaime.happyhourfinder.bar.mapper.BarMapper;
import com.mathieuaime.happyhourfinder.bar.service.BarService;
import com.mathieuaime.happyhourfinder.common.constants.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Paths.VERSION + Paths.Bar.BARS)
public class BarController {

  private final BarService barService;
  private final BarMapper barMapper;

  @Autowired
  public BarController(BarService barService, BarMapper barMapper) {
    this.barService = barService;
    this.barMapper = barMapper;
  }

  @GetMapping(Paths.STATUS)
  public String status() {
    return "working";
  }

  @GetMapping
  public Page<BarDto> getBars(Pageable pageable) {
    return barService.findAll(pageable).map(barMapper::convertToDto);
  }

  @GetMapping("/{id}")
  public BarDto getBar(@PathVariable Long id) {
    return barService.findById(id)
        .map(barMapper::convertToDto)
        .orElseThrow(BarNotFoundException::new);
  }

  @PostMapping
  public BarDto saveBar(@RequestBody BarDto barDto) {
    com.mathieuaime.happyhourfinder.bar.model.Bar bar = barService
        .save(barMapper.convertToEntity(barDto));
    return barMapper.convertToDto(bar);
  }

  @DeleteMapping("/{id}")
  public void deleteBar(@PathVariable Long id) {
    barService.deleteById(id);
  }
}
