package com.mathieuaime.happyhourfinder.config;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.repository.init.AbstractRepositoryPopulatorFactoryBean;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
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
  public AbstractRepositoryPopulatorFactoryBean repositoryPopulator() {

    ObjectMapper mapper = new ObjectMapper();
    mapper.addMixIn(GeoJsonPoint.class, GeoJsonPointMixin.class);
    mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

    Jackson2RepositoryPopulatorFactoryBean factoryBean = new Jackson2RepositoryPopulatorFactoryBean();
    factoryBean.setResources(new Resource[]{new ClassPathResource("starbucks-in-nyc.json")});
    factoryBean.setMapper(mapper);

    return factoryBean;
  }

  abstract static class GeoJsonPointMixin {

    GeoJsonPointMixin(@JsonProperty("longitude") double x, @JsonProperty("latitude") double y) {
    }
  }
}
