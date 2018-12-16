package com.mathieuaime.happyhourfinder.bar.controller;

import com.mathieuaime.happyhourfinder.bar.dto.BarDto;
import com.mathieuaime.happyhourfinder.bar.exception.BarNotFoundException;
import com.mathieuaime.happyhourfinder.bar.model.Bar;
import com.mathieuaime.happyhourfinder.bar.service.BarService;
import com.mathieuaime.happyhourfinder.common.constants.Paths;
import org.modelmapper.ModelMapper;
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
@RequestMapping(value = Paths.VERSION + Paths.BARS)
public class BarController {

  private final BarService barService;

  private final ModelMapper modelMapper;

  @Autowired
  public BarController(BarService barService, ModelMapper modelMapper) {
    this.barService = barService;
    this.modelMapper = modelMapper;
  }

  @GetMapping("/status/check")
  public String status() {
    return "working";
  }

  @GetMapping
  public Page<BarDto> getBars(Pageable pageable) {
    return barService.findAll(pageable)
        .map(this::convertToDto);
  }

  /**
   * Find a bar by its id.
   *
   * @param id the id
   * @return the bar dto
   */
  @GetMapping("/{id}")
  public BarDto getBar(@PathVariable Long id) {
    return barService.findById(id)
        .map(this::convertToDto)
        .orElseThrow(BarNotFoundException::new);
  }

  @PostMapping
  public BarDto saveBar(@RequestBody BarDto barDto) {
    Bar bar = barService.save(convertToEntity(barDto));
    return convertToDto(bar);
  }

  @DeleteMapping("/{id}")
  public void deleteBar(@PathVariable Long id) {
    barService.deleteById(id);
  }

  private BarDto convertToDto(Bar bar) {
    return modelMapper.map(bar, BarDto.class);
  }

  private Bar convertToEntity(BarDto barDto) {
    return modelMapper.map(barDto, Bar.class);
  }
}
