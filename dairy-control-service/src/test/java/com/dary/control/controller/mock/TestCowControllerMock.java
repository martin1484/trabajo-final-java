package com.dary.control.controller.mock;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.dary.control.controller.CowController;
import com.dary.control.dto.BaseDto;
import com.dary.control.dto.CowDto;
import com.dary.control.service.CowService;

public class TestCowControllerMock {
	
	@Mock
	private CowService cowService;
	
	@InjectMocks
	private CowController controller;
	
	private List<BaseDto> cows;
	
	@Before
	public void setUp() throws Exception {
		cows = new ArrayList<>();
		cows.add(new CowDto(Long.valueOf("321"), "Lola"));
		cows.add(new CowDto(Long.valueOf("123"), "Pepa"));
        MockitoAnnotations.initMocks(this);
		Mockito.when(cowService.findCowByEarMark(123L)).thenReturn(new CowDto(123L, "Pepa"));
		Mockito.<List<? extends BaseDto>>when(cowService.findAll()).thenReturn(cows);

	}

	@Test
	public void testGetCowByEarMark() {
		CowDto cowDto = controller.getCowByEarMark(123L);
		assertEquals("Pepa",cowDto.getName());
		Mockito.verify(cowService).findCowByEarMark(123L);
	}
	
	@Test
	public void testGetAllCows() {
		assertEquals(cows.size(), controller.getAllCows().size());
	}

}
