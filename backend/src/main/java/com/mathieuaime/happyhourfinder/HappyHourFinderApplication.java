package com.mathieuaime.happyhourfinder;

import com.mathieuaime.happyhourfinder.bar.dao.BarDao;
import com.mathieuaime.happyhourfinder.bar.model.Bar;
import java.util.stream.LongStream;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class HappyHourFinderApplication {

  public static void main(String[] args) {
    SpringApplication.run(HappyHourFinderApplication.class, args);
  }

  @Bean
  ApplicationRunner init(BarDao repository) {
    return args -> LongStream.range(1,100).forEach(i -> {
      Bar bar = Bar.builder().name("Bar" + i).build();
      repository.save(bar);
    });
  }
}
