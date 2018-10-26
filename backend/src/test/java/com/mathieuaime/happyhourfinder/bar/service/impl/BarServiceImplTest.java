package com.mathieuaime.happyhourfinder.bar.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.mathieuaime.happyhourfinder.bar.dao.BarDao;
import com.mathieuaime.happyhourfinder.bar.model.Bar;
import com.mathieuaime.happyhourfinder.bar.service.BarService;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;
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

@RunWith(MockitoJUnitRunner.class)
public class BarServiceImplTest {

  @Mock
  private BarDao barDao;

  private BarService mockBarService;

  private static final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(),
      26910);

  private static final Bar BAR_1 = Bar.builder().id(1L).name("Bar1")
      .coordinates(geometryFactory.createPoint(new Coordinate(1, 2))).build();
  private static final Bar BAR_2 = Bar.builder().id(2L).name("Bar2")
      .coordinates(geometryFactory.createPoint(new Coordinate(2, 3))).build();

  @Before
  public void setUp() throws Exception {
    mockBarService = new BarServiceImpl(barDao);
  }

  @Test
  public void testFindAll() throws Exception {
    Page<Bar> bars = new PageImpl<>(Arrays.asList(BAR_1, BAR_2));

    when(barDao.findAll(any(Pageable.class))).thenReturn(bars);
    Page<Bar> retrivedBars = mockBarService.findAll(Pageable.unpaged());

    assertEquals(bars, retrivedBars);

    verify(barDao, times(1)).findAll(any(Pageable.class));
    verifyNoMoreInteractions(barDao);
  }

  @Test
  public void testFindById() throws Exception {
    when(barDao.findById(BAR_1.getId())).thenReturn(Optional.of(BAR_1));
    Optional<Bar> retrivedBar = mockBarService.findById(BAR_1.getId());

    assertTrue(retrivedBar.isPresent());
    assertEquals(BAR_1, retrivedBar.get());

    verify(barDao, times(1)).findById(anyLong());
    verifyNoMoreInteractions(barDao);
  }

  @Test
  public void testSave() throws Exception {
    Bar bar = Bar.builder().name("Bar1").build();
    when(barDao.save(bar)).then(invocationOnMock -> {
      Bar invocBar = invocationOnMock.getArgument(0);
      invocBar.setId(1L);
      return invocBar;
    });

    Bar retrivedBar = mockBarService.save(bar);

    assertEquals(Long.valueOf(1L), retrivedBar.getId());
    assertEquals("Bar1", retrivedBar.getName());

    verify(barDao, times(1)).save(any(Bar.class));
    verifyNoMoreInteractions(barDao);
  }

  @Test
  public void testDeleteById() throws Exception {
    doNothing().when(barDao).deleteById(BAR_1.getId());
    mockBarService.deleteById(BAR_1.getId());

    verify(barDao, times(1)).deleteById(anyLong());
    verifyNoMoreInteractions(barDao);
  }

}