package com.mathieuaime.happyhourfinder.bar.controller;

import static com.mathieuaime.happyhourfinder.common.constants.Paths.CONFIG;
import static com.mathieuaime.happyhourfinder.common.constants.Paths.MAPS_API_KEY;
import static com.mathieuaime.happyhourfinder.common.constants.Paths.VERSION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ConfigController.class)
@TestPropertySource(locations = "classpath:google.properties")
public class ConfigControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void getStatus() throws Exception {
    mockMvc.perform(get(VERSION + CONFIG + MAPS_API_KEY)
        .contentType(MediaType.TEXT_PLAIN))
        .andExpect(status().isOk())
        .andExpect(content().string("MAPS_API_KEY"));
  }
}