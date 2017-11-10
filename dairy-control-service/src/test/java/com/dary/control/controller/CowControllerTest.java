package com.dary.control.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.dary.control.dto.CowDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CowControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIndex() {
		String body = this.restTemplate.getForObject("/", String.class);
		assertEquals(body,"Hello World");
	}
	
	@Test
	public void testGetCowByEarMark() {
		CowDto cowDto = this.restTemplate.getForObject("/cow/123", CowDto.class);
		assertEquals(cowDto.getName(),"Meli");
	}
	

}
