package com.mathieuaime.happyhourfinder.mapper.bar;

import com.mathieuaime.happyhourfinder.api.bar.BarDto;
import com.mathieuaime.happyhourfinder.model.bar.Bar;
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
