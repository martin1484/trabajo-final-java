package com.dary.control.repository;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.dary.control.model.Cow;
import com.dary.control.model.MilkProduction;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MilkProductionRepositoryTest {

	@Autowired
	private MilkProductionRepository milkProductionRepository;
	
	@Autowired
	private CowRepository cowRepository;
	
	private Cow cowTest;
	private MilkProduction milkProductionTest1;
	private MilkProduction milkProductionTest2;
	
	@Before
	public void setUp() throws Exception {
		cowTest = new Cow();
		cowTest.setEarMark(1234L);
		cowTest.setName("Test Cow");
		cowTest = cowRepository.save(cowTest);
		milkProductionTest1 = new MilkProduction();
		milkProductionTest2 = new MilkProduction();
		
		milkProductionTest1.setCow(cowTest);
		milkProductionTest1.setDate(new Date());
		milkProductionTest1.setLiters(20.1);
		milkProductionTest1.setTime(new Date());
		
		milkProductionTest2.setCow(cowTest);
		milkProductionTest2.setDate(new Date());
		milkProductionTest2.setLiters(25.1);
		
		String myTime = "17:30:54";
	    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
	    Date time = null;
	    try {
	        time = sdf.parse(myTime);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
		milkProductionTest2.setTime(time);
		
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testASaveMilkProduction() {
		milkProductionTest1 = milkProductionRepository.save(milkProductionTest1);
		assertNotNull(milkProductionTest1);
		milkProductionTest2 = milkProductionRepository.save(milkProductionTest2);
		assertNotNull(milkProductionTest2);
	}
	
	@Test
	public void testBFindByDateAndCowIdOrderByTimeAsc() {
		List<MilkProduction> productions = milkProductionRepository.findByCowIdAndDateOrderByTimeAsc(1L, new Date());
		
		assertNotNull(productions);
		assertFalse(productions.isEmpty());
	}
	
}
