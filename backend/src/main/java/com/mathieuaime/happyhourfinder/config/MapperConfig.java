package com.mathieuaime.happyhourfinder.config;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;

@Configuration
public class MapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    localTimeMapper(modelMapper);
    durationMapper(modelMapper);
    return modelMapper;
  }

  private void localTimeMapper(ModelMapper modelMapper) {
    Converter<String, LocalTime> fromStringDate = new AbstractConverter<String, LocalTime>() {
      @Override
      protected LocalTime convert(String source) {
        return LocalTime.parse(source);
      }
    };

    modelMapper.createTypeMap(String.class, LocalTime.class);
    modelMapper.addConverter(fromStringDate);
  }

  private void durationMapper(ModelMapper modelMapper) {
    Converter<String, Duration> fromStringDuration = new AbstractConverter<String, Duration>() {
      @Override
      protected Duration convert(String source) {
        return Duration.parse(source);
      }
    };

    modelMapper.createTypeMap(String.class, Duration.class);
    modelMapper.addConverter(fromStringDuration);
  }

  @Bean
  public Formatter<LocalTime> localDateFormatter() {
    return new Formatter<LocalTime>() {
      @NotNull
      @Override
      public LocalTime parse(@NotNull String text, @NotNull Locale locale) {
        return LocalTime.parse(text, DateTimeFormatter.ISO_TIME);
      }

      @NotNull
      @Override
      public String print(@NotNull LocalTime object, @NotNull Locale locale) {
        return DateTimeFormatter.ISO_TIME.format(object);
      }
    };
  }
}
