package com.mathieuaime.happyhourfinder.bar.controller;

import static com.mathieuaime.happyhourfinder.common.constants.Paths.BARS;
import static com.mathieuaime.happyhourfinder.common.constants.Paths.STATUS;
import static com.mathieuaime.happyhourfinder.common.constants.Paths.VERSION;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mathieuaime.happyhourfinder.bar.dto.BarDto;
import com.mathieuaime.happyhourfinder.bar.model.Bar;
import com.mathieuaime.happyhourfinder.bar.service.BarService;
import java.util.Arrays;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(BarController.class)
public class BarControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BarService barService;

  private ObjectMapper objectMapper = new ObjectMapper();
  private static final Bar BAR_1 = Bar.builder().id(1L).name("Bar").build();
  private static final Bar BAR_2 = Bar.builder().id(2L).name("Bar2").build();

  @Test
  public void getStatus() throws Exception {
    mockMvc.perform(get(VERSION + BARS + STATUS)
        .contentType(MediaType.TEXT_PLAIN))
        .andExpect(status().isOk())
        .andExpect(content().string("working"));

    verifyNoMoreInteractions(barService);
  }

  @Test
  public void getBars() throws Exception {
    Page<Bar> paginableBar = new PageImpl<>(Arrays.asList(BAR_1, BAR_2));

    when(barService.findAll(any(Pageable.class))).thenReturn(paginableBar);

    mockMvc.perform(get(VERSION + BARS)
        .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content", hasSize(2)))
        .andExpect(jsonPath("$.content[0].id", is(1)))
        .andExpect(jsonPath("$.content[0].name", is("Bar")))
        .andExpect(jsonPath("$.content[1].id", is(2)))
        .andExpect(jsonPath("$.content[1].name", is("Bar2")));

    verify(barService, times(1)).findAll(any(Pageable.class));
    verifyNoMoreInteractions(barService);
  }

  @Test
  public void getBarById() throws Exception {
    when(barService.findById(BAR_1.getId())).thenReturn(Optional.of(BAR_1));

    mockMvc.perform(get(VERSION + BARS + "{id}", 1L)
        .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.name", is("Bar")));

    verify(barService, times(1)).findById(anyLong());
    verifyNoMoreInteractions(barService);
  }

  @Test
  public void getBarByIdNotFound() throws Exception {
    when(barService.findById(1L)).thenReturn(Optional.empty());

    mockMvc.perform(get(VERSION + BARS + "{id}", 1L)
        .contentType(APPLICATION_JSON))
        .andExpect(status().isNotFound());

    verify(barService, times(1)).findById(anyLong());
    verifyNoMoreInteractions(barService);
  }

  @Test
  public void saveBar() throws Exception {
    BarDto barDto = new BarDto();
    barDto.setName("Bar");

    when(barService.save(any(Bar.class))).then(invocationOnMock -> {
      Bar bar = invocationOnMock.getArgument(0);
      bar.setId(1L);
      return bar;
    });

    mockMvc.perform(post(VERSION + BARS)
        .contentType(APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(barDto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.name", is("Bar")));

    verify(barService, times(1)).save(any(Bar.class));
    verifyNoMoreInteractions(barService);
  }

  @Test
  public void removeBar() throws Exception {
    doNothing().when(barService).deleteById(1L);

    mockMvc.perform(delete(VERSION + BARS + "{id}", 1L))
        .andExpect(status().isOk());

    verify(barService, times(1)).deleteById(1L);
    verifyNoMoreInteractions(barService);
  }
}