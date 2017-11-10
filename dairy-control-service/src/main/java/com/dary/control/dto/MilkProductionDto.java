package com.dary.control.dto;

import java.util.ArrayList;
import java.util.List;

public class MilkProductionDto extends BaseDto{
	
	/*
	 * Only used for save or update
	 */
	private Double liters;
	
	private Long earMark;
	
	private String date;
	
	private List<MilkProductionDetailDto> productionDetails;

	public MilkProductionDto() {
		super();
		this.productionDetails = new ArrayList<>();
	}

	public MilkProductionDto(Long earMark, String date, List<MilkProductionDetailDto> productionDetails) {
		super();
		this.earMark = earMark;
		this.date = date;
		this.productionDetails = productionDetails;
	}

	public Double getLiters() {
		return liters;
	}

	public void setLiters(Double liters) {
		this.liters = liters;
	}

	public Long getEarMark() {
		return earMark;
	}

	public void setEarMark(Long earMark) {
		this.earMark = earMark;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<MilkProductionDetailDto> getProductionDetails() {
		return productionDetails;
	}

	public void setProductionDetails(List<MilkProductionDetailDto> productionDetails) {
		this.productionDetails = productionDetails;
	}

	@Override
	public String toString() {
		return "MilkProductionDto [earMark=" + earMark + ", date=" + date + ", productionDetails=" + productionDetails
				+ "]";
	}

}
