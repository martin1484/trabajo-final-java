package com.dary.control.service;

import java.util.Date;
import java.util.List;

import com.dary.control.dto.MilkProductionDto;

public interface MilkProductionService extends BaseService{

	MilkProductionDto findMilkProductionById(Long id);
	
	List<MilkProductionDto> findProductionsByCowId(Long cowId);
	
	List<MilkProductionDto> findProductionsByCowIdAndDate(Long cowId, Date date);
	
}
