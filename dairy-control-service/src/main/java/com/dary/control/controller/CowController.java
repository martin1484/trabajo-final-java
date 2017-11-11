package com.dary.control.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.dary.control.dto.CowDto;
import com.dary.control.service.CowService;

@RestController
@RequestMapping("/cow")
public class CowController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CowController.class);
	
	@Autowired
	private CowService cowService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CowDto> getAllCows(){
		LOGGER.info("Get all cows");
		return (List<CowDto>) cowService.findAll();
	}
		
	@RequestMapping(value="/{earMark}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CowDto getCowByEarMark(@PathVariable Long earMark){
		LOGGER.info("Find Cow by earMark: "+ earMark);
		final CowDto cowDto = cowService.findCowByEarMark(earMark);
		return cowDto;
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CowDto saveCow(@RequestBody CowDto cowDto) {
		LOGGER.info("Save a Cow with earMark: "+cowDto.getEarMark());
		final CowDto newCowDto = (CowDto) cowService.saveOrUpdate(cowDto);
		return newCowDto;
	}
}
