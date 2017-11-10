package com.dary.control.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.dary.control.model.MilkProduction;

public interface MilkProductionRepository extends PagingAndSortingRepository<MilkProduction, Long> {

	List<MilkProduction> findByCowId(Long cowId);
	
	List<MilkProduction> findByCowIdAndDateOrderByTimeAsc(Long cowId, Date date);
	
}
