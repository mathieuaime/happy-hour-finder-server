package com.mathieuaime.happyhourfinder.bar.mapper;

import com.mathieuaime.happyhourfinder.bar.dto.BarDto;
import com.mathieuaime.happyhourfinder.bar.model.Bar;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BarMapper {

  private final ModelMapper modelMapper;

  @Autowired
  public BarMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public BarDto convertToDto(Bar bar) {
    return modelMapper.map(bar, BarDto.class);
  }

  public Bar convertToEntity(BarDto barDto) {
    return modelMapper.map(barDto, Bar.class);
  }
}
