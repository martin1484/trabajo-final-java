package com.dary.control.controller.mock;

import com.dary.control.controller.CowController;
import com.dary.control.dto.BaseDto;
import com.dary.control.dto.CowDto;
import com.dary.control.service.CowService;
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
public class MockCowControllerTest {

    @Mock
    private CowService cowService;

    @InjectMocks
    private CowController cowController;

    private CowDto testCowDto;
    private List<CowDto> cowDtos;

    @Before
    public void init() throws Exception {
        cowDtos = new ArrayList<>();
        testCowDto = new CowDto();
        testCowDto.setName("Test Cow");
        testCowDto.setEarMark(1234L);
        testCowDto.setDaryProducerId(1L);
        testCowDto.setId(1L);
        cowDtos.add(testCowDto);
        cowDtos.add(testCowDto);
        Mockito.<List<? extends BaseDto>>when(cowService.findAll()).thenReturn(cowDtos);
        Mockito.when(cowService.findCowByEarMark(1234L)).thenReturn(testCowDto);
        Mockito.when(cowService.saveOrUpdate(testCowDto)).thenReturn(testCowDto);
    }

    @Test
    public void getAllCows() throws Exception {
        assertNotNull("Expected a not null array",cowController.getAllCows());
        assertEquals(cowDtos.size(),cowController.getAllCows().size());
    }

    @Test
    public void getCowByEarMark() throws Exception {
        assertEquals("Expected equals cowDto names",testCowDto.getName(),cowController.getCowByEarMark(1234L).getName());
    }

    @Test
    public void saveCow() throws Exception {
        assertEquals("Expected equals cowDto Id",testCowDto.getEarMark(),cowController.saveCow(testCowDto).getEarMark());
    }

}