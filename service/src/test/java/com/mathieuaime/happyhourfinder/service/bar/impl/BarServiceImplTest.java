package com.mathieuaime.happyhourfinder.service.bar.impl;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
      new Bar().uuid("uuid-1").name("Bar1").coordinates(new GeoJsonPoint(1, 2));
  private static final Bar BAR_2 =
      new Bar().uuid("uuid-2").name("Bar2").coordinates(new GeoJsonPoint(2, 3));

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
  public void testFindByUuid() {
    when(barDao.findById(BAR_1.getUuid())).thenReturn(Optional.of(BAR_1));
    Optional<Bar> retrievedBar = mockBarService.findByUuid(BAR_1.getUuid());

    assertTrue(retrievedBar.isPresent());
    assertEquals(BAR_1, retrievedBar.get());

    verify(barDao).findById(anyString());
    verifyNoMoreInteractions(barDao);
  }

  @Test
  public void testSave() {
    Bar bar = new Bar().name("Bar3");
    when(barDao.save(bar)).then(invocationOnMock -> {
      Bar invocBar = invocationOnMock.getArgument(0);
      return invocBar.uuid("uuid-3");
    });

    Bar savedBar = mockBarService.save(bar);

    assertEquals("uuid-3", savedBar.getUuid());
    assertEquals("Bar3", savedBar.getName());

    verify(barDao).save(any(Bar.class));
    verifyNoMoreInteractions(barDao);
  }

  @Test
  public void testDeleteById() {
    doNothing().when(barDao).deleteById(BAR_1.getUuid());
    mockBarService.deleteByUuid(BAR_1.getUuid());

    verify(barDao).deleteById(BAR_1.getUuid());
    verifyNoMoreInteractions(barDao);
  }
}