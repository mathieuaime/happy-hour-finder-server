package com.mathieuaime.happyhourfinder.service.bar.impl;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.mathieuaime.happyhourfinder.model.bar.Bar;
import com.mathieuaime.happyhourfinder.repository.bar.BarDao;
import com.mathieuaime.happyhourfinder.service.bar.BarService;
import java.util.Arrays;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@RunWith(MockitoJUnitRunner.class)
public class BarServiceImplTest {

  @Mock
  private BarDao barDao;

  private BarService mockBarService;

  private static final Bar BAR_1 =
      new Bar().id(1L).name("Bar1").coordinates(new GeoJsonPoint(1, 2));
  private static final Bar BAR_2 =
      new Bar().id(2L).name("Bar2").coordinates(new GeoJsonPoint(2, 3));

  @Before
  public void setUp() {
    mockBarService = new BarServiceImpl(barDao);
  }

  @Test
  public void testFindAll() {
    Page<Bar> bars = new PageImpl<>(Arrays.asList(BAR_1, BAR_2));

    when(barDao.findAll(any(Pageable.class))).thenReturn(bars);
    Page<Bar> retrivedBars = mockBarService.findAll(Pageable.unpaged());

    assertEquals(bars, retrivedBars);

    verify(barDao).findAll(any(Pageable.class));
    verifyNoMoreInteractions(barDao);
  }

  @Test
  public void testFindById() {
    when(barDao.findById(BAR_1.getId())).thenReturn(Optional.of(BAR_1));
    Optional<Bar> retrievedBar = mockBarService.findById(BAR_1.getId());

    assertTrue(retrievedBar.isPresent());
    assertEquals(BAR_1, retrievedBar.get());

    verify(barDao).findById(anyLong());
    verifyNoMoreInteractions(barDao);
  }

  @Test
  public void testSave() {
    Bar bar = new Bar().name("Bar1");
    when(barDao.save(bar)).then(invocationOnMock -> {
      Bar invocBar = invocationOnMock.getArgument(0);
      invocBar.setId(1L);
      return invocBar;
    });

    Bar savedBar = mockBarService.save(bar);

    assertEquals(Long.valueOf(1L), savedBar.getId());
    assertEquals("Bar1", savedBar.getName());

    verify(barDao).save(any(Bar.class));
    verifyNoMoreInteractions(barDao);
  }

  @Test
  public void testDeleteById() {
    doNothing().when(barDao).deleteById(BAR_1.getId());
    mockBarService.deleteById(BAR_1.getId());

    verify(barDao).deleteById(anyLong());
    verifyNoMoreInteractions(barDao);
  }
}