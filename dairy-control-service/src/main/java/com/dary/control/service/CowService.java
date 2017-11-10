package com.dary.control.service;

import com.dary.control.dto.CowDto;

public interface CowService extends BaseService{
	
	CowDto findCowById(Long id);
	
	CowDto findCowByEarMark(Long earMark);
	
	void deleteByEarMark(Long earMark);

}
