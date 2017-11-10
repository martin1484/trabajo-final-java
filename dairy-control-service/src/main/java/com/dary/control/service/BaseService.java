package com.dary.control.service;

import java.util.List;

import com.dary.control.dto.BaseDto;
import com.dary.control.model.BaseEntity;

public interface BaseService {
	
	BaseDto saveOrUpdate(BaseDto baseDto);
	
	Boolean exist(BaseDto baseDto);
	
	BaseDto convertToDto(BaseEntity entity);
	
	BaseEntity convertToEntity(BaseDto dto);
	
	void delete(Long id);

	List<? extends BaseDto> findAll();
	
}
