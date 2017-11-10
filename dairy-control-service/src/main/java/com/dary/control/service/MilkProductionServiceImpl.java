package com.dary.control.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dary.control.dto.BaseDto;
import com.dary.control.dto.MilkProductionDetailDto;
import com.dary.control.dto.MilkProductionDto;
import com.dary.control.model.BaseEntity;
import com.dary.control.model.Cow;
import com.dary.control.model.MilkProduction;
import com.dary.control.repository.CowRepository;
import com.dary.control.repository.MilkProductionRepository;

@Service("milkProductionService")
public class MilkProductionServiceImpl implements MilkProductionService {

	@Autowired
	private MilkProductionRepository milkProductionRepository;

	@Autowired
	private CowRepository cowRepository;

	@Override
	public BaseDto saveOrUpdate(BaseDto baseDto) {
		final MilkProduction milkProduction = this.convertToEntity(baseDto);
		final MilkProductionDto milkProductionDto = this.convertToDto(milkProductionRepository.save(milkProduction));
		return milkProductionDto;
	}

	@Override
	public MilkProductionDto findMilkProductionById(Long id) {
		final MilkProductionDto milkProductionDto = this.convertToDto(milkProductionRepository.findOne(id));
		return milkProductionDto;
	}

	@Override
	public Boolean exist(BaseDto baseDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<? extends BaseDto> findAll() {
		final List<MilkProduction> productions = (List<MilkProduction>) milkProductionRepository.findAll();
		List<MilkProductionDto> productionsDto = new ArrayList<MilkProductionDto>();
		productionsDto = createDtoList(productions);
		return productionsDto;
	}

	@Override
	public List<MilkProductionDto> findProductionsByCowIdAndDate(Long cowId, Date date) {
		final List<MilkProduction> productions = (List<MilkProduction>) milkProductionRepository
				.findByCowIdAndDateOrderByTimeAsc(cowId, date);
		List<MilkProductionDto> productionsDto = new ArrayList<MilkProductionDto>();
		for (MilkProduction milkProduction : productions) {
			productionsDto.add(convertToDto(milkProduction));
		}
		return productionsDto;
	}

	@Override
	public List<MilkProductionDto> findProductionsByCowId(Long cowId) {
		final List<MilkProduction> productions = (List<MilkProduction>) milkProductionRepository.findByCowId(cowId);
		List<MilkProductionDto> productionsDto = new ArrayList<MilkProductionDto>();
		for (MilkProduction milkProduction : productions) {
			productionsDto.add(convertToDto(milkProduction));
		}
		return productionsDto;
	}

	@Override
	public MilkProductionDto convertToDto(BaseEntity entity) {
		final MilkProduction milkProduction = (MilkProduction) entity;
		final MilkProductionDto milkProductionDto = new MilkProductionDto();
		milkProductionDto.setDate(milkProduction.getDate().toString());
		milkProductionDto.setEarMark(milkProduction.getCow().getEarMark());
		milkProductionDto.setLiters(milkProduction.getLiters());
		return milkProductionDto;
	}

	@Override
	public MilkProduction convertToEntity(BaseDto baseDto) {
		final MilkProductionDto milkProductionDto = (MilkProductionDto) baseDto;
		final Cow cow = cowRepository.findByEarMark(milkProductionDto.getEarMark());
		MilkProduction milkProduction;
		milkProduction = new MilkProduction();
		milkProduction.setDate(new Date());
		milkProduction.setTime(new Date());
		milkProduction.setLiters(milkProductionDto.getLiters());
		milkProduction.setCow(cow);
		return milkProduction;
	}

	private List<MilkProductionDto> createDtoList(List<MilkProduction> productions) {
		Map<Long, Map<String, MilkProductionDto>> productionsDtoMap = new HashMap<>();
		MilkProductionDto milkProductionDto;
		for (MilkProduction milkProduction : productions) {
			Map<String, MilkProductionDto> map = productionsDtoMap.get(milkProduction.getCow().getEarMark());
			if (map == null) {
				map = new HashMap<>();
				milkProductionDto = this.convertToDto(milkProduction);
			}else {
				milkProductionDto = map.get(milkProduction.getDate().toString());
				if(milkProductionDto == null) {
					milkProductionDto = this.convertToDto(milkProduction);
				}
			}
			List<MilkProductionDetailDto> details = milkProductionDto.getProductionDetails();
			details.add(new MilkProductionDetailDto(milkProduction.getId(), milkProduction.getLiters(), milkProduction.getTime().toString()));
			milkProductionDto.setProductionDetails(details);
			map.put(milkProductionDto.getDate(), milkProductionDto);
			productionsDtoMap.put(milkProduction.getCow().getEarMark(), map);
		}
		List<MilkProductionDto> finalDtoList = new ArrayList<>();
		for (Entry<Long, Map<String, MilkProductionDto>> entry : productionsDtoMap.entrySet()) {
			List<MilkProductionDto> list = new ArrayList<MilkProductionDto>(entry.getValue().values());
		    finalDtoList.addAll(list);
		}
		return finalDtoList;
	}

}
