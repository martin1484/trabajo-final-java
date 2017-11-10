package com.dary.control.service;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import com.dary.control.dto.MilkProductionDto;
import com.dary.control.model.DaryProducer;
import com.dary.control.repository.DaryProducerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MilkProductionServiceTest {

	@Autowired
	private CowService cowService;

	@Autowired
	private MilkProductionService milkProductionService;

	@Autowired
	private DaryProducerRepository daryProducerRepository;

	private CowDto cowDtoTest;
	private MilkProductionDto milkProductionDto1;
	private MilkProductionDto milkProductionDto2;
	private MilkProductionDto milkProductionDto3;
	private DaryProducer daryProducer;
	private Date today;

	@Before
	public void setUp() throws Exception {
		String dateString = "2017-09-16";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		today = sdf.parse(dateString);
		today = Calendar.getInstance().getTime();

		daryProducer = new DaryProducer();
		daryProducer.setName("Beltramino");
		daryProducer = daryProducerRepository.save(daryProducer);

		final Long daryProducerId = daryProducer.getId();
		cowDtoTest = new CowDto(123L, "Meli");
		cowDtoTest.setDaryProducerId(daryProducerId);
		cowService.saveOrUpdate(cowDtoTest);

		milkProductionDto1 = new MilkProductionDto();
		milkProductionDto1.setEarMark(123L);
		milkProductionDto1.setLiters(20.0);

		milkProductionDto2 = new MilkProductionDto();
		milkProductionDto2.setEarMark(123L);
		milkProductionDto2.setLiters(25.4);

		milkProductionDto3 = new MilkProductionDto();
		milkProductionDto3.setEarMark(123L);
		milkProductionDto3.setLiters(25.4);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testASaveProduction() {
		milkProductionDto1 = (MilkProductionDto) milkProductionService.saveOrUpdate(milkProductionDto1);
		assertNotNull(milkProductionDto1);
		milkProductionDto2 = (MilkProductionDto) milkProductionService.saveOrUpdate(milkProductionDto2);
		assertNotNull(milkProductionDto2);
		milkProductionDto3 = (MilkProductionDto) milkProductionService.saveOrUpdate(milkProductionDto3);
		assertNotNull(milkProductionDto3);
	}

	@Test
	public void testBFindProductionByCowIdAndDate() {
		final Long cowId = cowService.findCowByEarMark(123L).getId();
		List<MilkProductionDto> productions = milkProductionService.findProductionsByCowIdAndDate(cowId, today);
		assertNotNull(productions);
		assertFalse(productions.isEmpty());
		assertEquals(productions.size(), 2);
	}

	@Test
	public void testBFindProductionByCowId() {
		final Long cowId = cowService.findCowByEarMark(123L).getId();
		List<MilkProductionDto> productions = milkProductionService.findProductionsByCowId(cowId);
		assertNotNull(productions);
		assertFalse(productions.isEmpty());
		assertEquals(productions.size(), 3);
	}
	
}
