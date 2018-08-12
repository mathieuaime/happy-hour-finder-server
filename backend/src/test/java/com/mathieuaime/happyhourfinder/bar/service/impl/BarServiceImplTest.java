package com.mathieuaime.happyhourfinder.bar.service.impl;

import com.mathieuaime.happyhourfinder.bar.dao.BarDAO;
import com.mathieuaime.happyhourfinder.bar.model.Bar;
import com.mathieuaime.happyhourfinder.bar.service.BarService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BarServiceImplTest {

    @Mock
    private BarDAO barDAO;

    private BarService mockBarService;
    private static final Bar BAR_1 = Bar.builder().id(1L).name("Bar1").build();
    private static final Bar BAR_2 = Bar.builder().id(2L).name("Bar2").build();

    @Before
    public void setUp() throws Exception {
        mockBarService = new BarServiceImpl(barDAO);
    }

    @Test
    public void testFindAll() throws Exception {
        Page<Bar> bars = new PageImpl<>(Arrays.asList(BAR_1, BAR_2));

        when(barDAO.findAll(any(Pageable.class))).thenReturn(bars);
        Page<Bar> retrivedBars = mockBarService.findAll(Pageable.unpaged());

        assertEquals(bars, retrivedBars);

        verify(barDAO, times(1)).findAll(any(Pageable.class));
        verifyNoMoreInteractions(barDAO);
    }

    @Test
    public void testFindById() throws Exception {
        when(barDAO.findById(BAR_1.getId())).thenReturn(Optional.of(BAR_1));
        Optional<Bar> retrivedBar = mockBarService.findById(BAR_1.getId());

        assertTrue(retrivedBar.isPresent());
        assertEquals(BAR_1, retrivedBar.get());

        verify(barDAO, times(1)).findById(anyLong());
        verifyNoMoreInteractions(barDAO);
    }

    @Test
    public void testSave() throws Exception {
        Bar bar = Bar.builder().name("Bar1").build();
        when(barDAO.save(bar)).then(invocationOnMock -> {
            Bar invocBar = invocationOnMock.getArgument(0);
            invocBar.setId(1L);
            return invocBar;
        });

        Bar retrivedBar = mockBarService.save(bar);

        assertEquals(Long.valueOf(1L), retrivedBar.getId());
        assertEquals("Bar1", retrivedBar.getName());

        verify(barDAO, times(1)).save(any(Bar.class));
        verifyNoMoreInteractions(barDAO);
    }

    @Test
    public void testDeleteById() throws Exception {
        doNothing().when(barDAO).deleteById(BAR_1.getId());
        mockBarService.deleteById(BAR_1.getId());

        verify(barDAO, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(barDAO);
    }

}