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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BarServiceImplTest {

    @Mock
    private BarDAO barDAO;

    private BarService mockBarService;

    @Before
    public void setUp() throws Exception {
        mockBarService = new BarServiceImpl(barDAO);
    }

    @Test
    public void testFindAll() throws Exception {
        Bar bar = new Bar();
        Bar bar2 = new Bar();
        Page<Bar> bars = new PageImpl<>(Arrays.asList(bar, bar2));

        when(barDAO.findAll(any(Pageable.class))).thenReturn(bars);
        Page<Bar> retrivedBars = mockBarService.findAll(Pageable.unpaged());

        assertEquals(bars, retrivedBars);
    }

    @Test
    public void testFindById() throws Exception {
        Bar bar = new Bar();
        bar.setId(1L);

        when(barDAO.findById(bar.getId())).thenReturn(Optional.of(bar));
        Optional<Bar> retrivedBar = mockBarService.findById(bar.getId());

        assertTrue(retrivedBar.isPresent());
        assertEquals(bar, retrivedBar.get());
    }

    @Test
    public void testSave() throws Exception {
        Bar bar = new Bar();

        when(barDAO.save(bar)).then(invocationOnMock -> {
            Bar invocBar = invocationOnMock.getArgument(0);
            invocBar.setId(1L);
            return invocBar;
        });

        Bar retrivedBar = mockBarService.save(bar);

        assertEquals(Long.valueOf(1L), retrivedBar.getId());
    }

    @Test
    public void testDeleteById() throws Exception {
        Bar bar = new Bar();
        bar.setId(1L);

        doNothing().when(barDAO).deleteById(bar.getId());
        mockBarService.deleteById(bar.getId());
    }

}