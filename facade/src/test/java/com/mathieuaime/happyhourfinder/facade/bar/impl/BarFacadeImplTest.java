package com.mathieuaime.happyhourfinder.facade.bar.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableList;
import com.mathieuaime.happyhourfinder.api.bar.BarDto;
import com.mathieuaime.happyhourfinder.facade.bar.BarFacade;
import com.mathieuaime.happyhourfinder.mapper.bar.BarMapper;
import com.mathieuaime.happyhourfinder.model.bar.Bar;
import com.mathieuaime.happyhourfinder.service.bar.BarService;
import java.util.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@RunWith(MockitoJUnitRunner.class)
public class BarFacadeImplTest {

  @Mock
  private BarService barService;
  @Mock
  private BarMapper barMapper;

  private BarFacade mockBarFacade;

  @Mock
  private Bar bar;
  @Mock
  private BarDto barDto;

  @Before
  public void setUp() {
    mockBarFacade = new BarFacadeImpl(barService, barMapper);
    when(barMapper.convertToDto(this.bar)).thenReturn(this.barDto);
    when(barMapper.convertToEntity(this.barDto)).thenReturn(this.bar);
  }

  @After
  public void tearDown() throws Exception {
    verifyNoMoreInteractions(barService, barMapper);
  }

  @Test
  public void findAllWithEmptyResult() throws Exception {
    Pageable pageable = Pageable.unpaged();
    when(barService.findAll(pageable)).thenReturn(Page.empty());

    Page<BarDto> barDtos = mockBarFacade.findAll(pageable);

    assertThat(barDtos.getTotalElements()).isEqualTo(0L);
    verify(barService).findAll(pageable);
  }

  @Test
  public void findAllWithResults() throws Exception {
    Pageable pageable = Pageable.unpaged();
    when(barService.findAll(pageable)).thenReturn(new PageImpl<>(ImmutableList.of(this.bar)));

    Page<BarDto> barDtos = mockBarFacade.findAll(pageable);

    assertThat(barDtos.getTotalElements()).isEqualTo(1L);
    verify(barService).findAll(pageable);
    verify(barMapper).convertToDto(this.bar);
  }

  @Test
  public void findByIdWithEmptyResult() throws Exception {
    when(barService.findByUuid("uuid-1")).thenReturn(Optional.empty());

    Optional<BarDto> barDto = mockBarFacade.findByUuid("uuid-1");

    assertThat(barDto).isNotPresent();
    verify(barService).findByUuid("uuid-1");
  }

  @Test
  public void findByIdWithResults() throws Exception {
    when(barService.findByUuid("uuid-1")).thenReturn(Optional.of(this.bar));

    Optional<BarDto> barDto = mockBarFacade.findByUuid("uuid-1");

    assertThat(barDto).isPresent();
    assertThat(barDto.get()).isEqualTo(this.barDto);
    verify(barService).findByUuid("uuid-1");
    verify(barMapper).convertToDto(this.bar);
  }

  @Test
  public void save() throws Exception {
    when(barService.save(bar)).thenReturn(this.bar);

    BarDto barDto = mockBarFacade.save(this.barDto);

    assertThat(barDto).isEqualTo(this.barDto);

    verify(barService).save(this.bar);
    verify(barMapper).convertToEntity(this.barDto);
    verify(barMapper).convertToDto(this.bar);
  }

  @Test
  public void deleteById() throws Exception {
    doNothing().when(barService).deleteByUuid("uuid-1");

    mockBarFacade.deleteByUuid("uuid-1");

    verify(barService).deleteByUuid("uuid-1");
  }
}
