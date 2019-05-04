package com.mathieuaime.happyhourfinder.config;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {

  @Value("${front.address}")
  private String address;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins(address)
        .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
        .allowedHeaders("X-Auth-Token", "Content-Type")
        .exposedHeaders("custom-header1", "custom-header2")
        .allowCredentials(false)
        .maxAge(4800);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
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
