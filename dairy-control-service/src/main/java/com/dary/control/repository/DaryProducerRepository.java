package com.dary.control.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.dary.control.model.DaryProducer;

public interface DaryProducerRepository extends PagingAndSortingRepository<DaryProducer, Long> {

	DaryProducer findByName(String name);
}
