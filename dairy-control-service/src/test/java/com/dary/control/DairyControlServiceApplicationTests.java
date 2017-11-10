package com.dary.control;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dary.control.model.Cow;
import com.dary.control.model.MilkProduction;
import com.dary.control.repository.CowRepository;
import com.dary.control.repository.MilkProductionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DairyControlServiceApplicationTests {

	@Autowired
	private CowRepository cowRepository;
	
	@Autowired
	private MilkProductionRepository milkProductionRepository;
	
	@Test
	public void contextLoads() {
		
		Cow c = cowRepository.save(new Cow());
		
		MilkProduction mp = new MilkProduction();
		mp.setCow(c);
		mp.setDate(new Date());
		mp.setTime(new Date());
		milkProductionRepository.save(mp);
		
	}

}
