package com.dary.control.service;

import com.dary.control.dto.CowDto;
import com.dary.control.model.Cow;
import com.dary.control.model.DaryProducer;
import com.dary.control.repository.CowRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MockCowServiceTest {

    @Mock
    private CowRepository cowRepository;

    @InjectMocks
    private CowServiceImpl cowService;

    @Before
    public void setUp() throws Exception {
        DaryProducer daryProducer = new DaryProducer();
        List<Cow> cows = new ArrayList<>();
        daryProducer.setId(1L);
        daryProducer.setName("Beltramino");
        Cow testCow1 = new Cow();
        testCow1.setId(1L);
        testCow1.setEarMark(1234L);
        testCow1.setName("Cow Test One");
        testCow1.setDaryProducer(daryProducer);
        cows.add(testCow1);
        cows.add(testCow1);
        Mockito.when(cowRepository.findOne(1L)).thenReturn(testCow1);
        Mockito.when(cowRepository.findByEarMark(1234L)).thenReturn(testCow1);
        Mockito.when(cowRepository.findAll()).thenReturn(cows);

    }

    @Test
    public void findCowById() throws Exception {
        CowDto cowDto = cowService.findCowById(1L);
        assertEquals("Cow Test One",cowDto.getName());
    }

    @Test
    public void findCowByEarMark() throws Exception {
        CowDto cowDto = cowService.findCowById(1L);
        assertEquals(Long.valueOf(1),cowDto.getId());
    }

    @Test
    public void findAll() throws Exception {
        assertTrue(cowService.findAll().size() == 2);
    }

}