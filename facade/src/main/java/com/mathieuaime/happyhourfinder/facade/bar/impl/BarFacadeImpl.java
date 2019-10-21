package com.mathieuaime.happyhourfinder.facade.bar.impl;

import com.mathieuaime.happyhourfinder.api.bar.BarDto;
import com.mathieuaime.happyhourfinder.facade.bar.BarFacade;
import com.mathieuaime.happyhourfinder.mapper.bar.BarMapper;
import com.mathieuaime.happyhourfinder.model.bar.Bar;
import com.mathieuaime.happyhourfinder.service.bar.BarService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BarFacadeImpl implements BarFacade {

  private BarService barService;
  private BarMapper barMapper;

  @Autowired
  public BarFacadeImpl(BarService barService, BarMapper barMapper) {
    this.barService = barService;
    this.barMapper = barMapper;
  }

  @Override
  public Page<BarDto> findAll(Pageable pageable) {
    return barService.findAll(pageable).map(barMapper::convertToDto);
  }

  @Override
  public Optional<BarDto> findByUuid(String uuid) {
    return barService.findByUuid(uuid).map(barMapper::convertToDto);
  }

  @Override
  public List<BarDto> findWithin(double lat, double lon, double distance) {
    return barService.findWithin(lat, lon, distance)
        .stream().map(barMapper::convertToDto).collect(Collectors.toList());
  }

  @Override
  public BarDto save(BarDto barDto) {
    Bar bar = barService.save(barMapper.convertToEntity(barDto));
    return barMapper.convertToDto(bar);
  }

  @Override
  public void deleteByUuid(String uuid) {
    barService.deleteByUuid(uuid);
  }
}
