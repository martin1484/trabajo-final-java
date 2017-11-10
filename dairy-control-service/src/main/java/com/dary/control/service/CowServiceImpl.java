package com.dary.control.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dary.control.dto.BaseDto;
import com.dary.control.dto.CowDto;
import com.dary.control.exception.BusinessExcepion;
import com.dary.control.model.BaseEntity;
import com.dary.control.model.Cow;
import com.dary.control.model.DaryProducer;
import com.dary.control.repository.CowRepository;
import com.dary.control.repository.DaryProducerRepository;

@Transactional
@Service("cowService")
public class CowServiceImpl implements CowService {
	
	final Logger LOGGER = LoggerFactory.getLogger(CowServiceImpl.class);
	
	@Autowired
	private CowRepository cowRepository;
	
	@Autowired
	private DaryProducerRepository daryProducerRepository;
	
	@Override
	public CowDto findCowById(Long id) {
		final Cow cow = cowRepository.findOne(id);
		return convertToDto(cow);
	}

	@Override
	public CowDto findCowByEarMark(Long earMark) {
		final Cow cow = cowRepository.findByEarMark(earMark);
		return convertToDto(cow);
	}
	
	@Override
	public CowDto saveOrUpdate(BaseDto baseDto) {
		LOGGER.info(String.format("Save or Update Cow with ear mark: %s.",((CowDto)baseDto).getEarMark()));
		final Cow cow = this.convertToEntity(baseDto);
		final CowDto cowDto = this.convertToDto(cowRepository.save(cow));
		return cowDto;
	}
	
	@Override
	public List<CowDto> findAll() {
		List<Cow> cows = (List<Cow>) cowRepository.findAll();
		List<CowDto> cowsDto = new ArrayList<CowDto>();
		for (Cow cow : cows) {
			cowsDto.add(this.convertToDto(cow));
		}
		return cowsDto;
	}

	
	@Override
	public CowDto convertToDto(BaseEntity entity) {
		final Cow cow = (Cow)entity;
		if(cow != null){
			return new CowDto(cow.getId(), cow.getEarMark(), cow.getName(), cow.getDaryProducer().getId());
		}else{
			return null;
		}
	
	}

	@Override
	public Cow convertToEntity(BaseDto baseDto) {
		final CowDto cowDto = (CowDto)baseDto;
		Cow cow = cowRepository.findByEarMark(cowDto.getEarMark());
		if(null == cow){
			cow = new Cow();
			cow.setEarMark(cowDto.getEarMark());
			cow.setName(cowDto.getName());
			final DaryProducer daryProducer = daryProducerRepository.findOne(cowDto.getDaryProducerId());
			if(daryProducer == null){
				LOGGER.error(String.format("No exist Dary Producer with id: %s.",cowDto.getDaryProducerId()));
				throw new BusinessExcepion(String.format("No exist Dary Producer with id: %s.",cowDto.getDaryProducerId()));
			}
			cow.setDaryProducer(daryProducer);
		}else{
			cow.setEarMark(cowDto.getEarMark());
			cow.setName(cowDto.getName());
		}
		return cow;
	}

	@Override
	public Boolean exist(BaseDto baseDto) {
		final CowDto cowDto = (CowDto)baseDto;
		return null != cowRepository.findByEarMark(cowDto.getEarMark());
	}

	@Override
	public void delete(Long id) {
		cowRepository.delete(id);
	}
	
	@Override
	public void deleteByEarMark(Long earMark) {
		final Long count = cowRepository.deleteByEarMark(earMark);
		if (count > 1){
			LOGGER.info("More than one entitie was deleted: "+count);
		}else{
			LOGGER.info("No entity was deleted");
		}
	}

}
