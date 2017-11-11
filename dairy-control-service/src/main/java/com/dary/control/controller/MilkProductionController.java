package com.dary.control.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dary.control.dto.MilkProductionDto;
import com.dary.control.service.MilkProductionService;

@RestController
@RequestMapping("/api/production")
public class MilkProductionController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MilkProductionController.class);

	@Autowired
	private MilkProductionService milkProductionService;
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public MilkProductionDto saveProduction(@RequestBody MilkProductionDto milkProductionDto) {
		LOGGER.info("Save a production: "+milkProductionDto);
		final MilkProductionDto newMilkProductionDto = (MilkProductionDto) milkProductionService.saveOrUpdate(milkProductionDto);
		return newMilkProductionDto;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MilkProductionDto> getAllProductions() {
		LOGGER.info("Get all productions");
		return (List<MilkProductionDto>) milkProductionService.findAll();
	}
	
}
