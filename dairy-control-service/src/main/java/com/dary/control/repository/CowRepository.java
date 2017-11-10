package com.dary.control.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.dary.control.model.Cow;

public interface CowRepository extends PagingAndSortingRepository<Cow, Long>{

	Cow findByEarMark(Long earMark);
	
	List<Cow> findByDaryProducerId(Long daryProducerId);
	
	/**
	 * The keywords remove and delete are supported. 
	 * As return value one can choose between the number or a list of removed entities.
	 * With this method you will remove one or more items
	 * 
	 * @param earMark
	 * @return number of removed entities
	 */
	Long deleteByEarMark(Long earMark);

	
}
