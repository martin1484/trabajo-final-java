package com.dary.control.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dary.control.dto.CowDto;
import com.dary.control.model.DaryProducer;
import com.dary.control.repository.DaryProducerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CowServiceTest {
	
	@Autowired
	private CowService cowService;
	
	@Autowired 
	private DaryProducerRepository daryProducerRepository;
	
	private CowDto cowDtoTest;
	private DaryProducer daryProducer;
	
	@Before
	public void setUp() throws Exception {
		daryProducer = new DaryProducer();
		daryProducer.setName("Beltramino");
		daryProducer = daryProducerRepository.save(daryProducer);
		final Long daryProducerId = daryProducer.getId();
		cowDtoTest = new CowDto(123L, "Meli");
		cowDtoTest.setDaryProducerId(daryProducerId);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testASaveCow() {
		CowDto cowDto = (CowDto) cowService.saveOrUpdate(cowDtoTest);
		assertNotNull(cowDto.getId());
		assertEquals("Meli", cowDto.getName());
	}
	
	@Test
	public void testBUpdateCow() {
		CowDto cowDto = (CowDto) cowService.findCowByEarMark(123L);
		cowDto.setName("Name Test Updated");
		cowDto = (CowDto) cowService.saveOrUpdate(cowDto);
		assertEquals("Name Test Updated",cowDto.getName());
	}
	
	@Test
	public void testCFindAll() {
		@SuppressWarnings("unchecked")
		List<CowDto> cows = (List<CowDto>) cowService.findAll();
		assertNotNull(cows);
		assertFalse(cows.isEmpty());
	}

	
	@Test
	public void testDdeleteCow() {
		cowService.deleteByEarMark(123L);
		CowDto cowDto = (CowDto) cowService.findCowByEarMark(123L);
		assertNull(cowDto);
	}
}
