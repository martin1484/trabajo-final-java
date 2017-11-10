package com.dary.control.dto;

public class MilkProductionDetailDto extends BaseDto{
	
	private Long productionId;
	
	private Double liters;
	
	private String time;

	public MilkProductionDetailDto() {
		super();
	}

	public MilkProductionDetailDto(Long productionId, Double liters, String time) {
		super();
		this.productionId = productionId;
		this.liters = liters;
		this.time = time;
	}

	public Long getProductionId() {
		return productionId;
	}

	public void setProductionId(Long productionId) {
		this.productionId = productionId;
	}

	public Double getLiters() {
		return liters;
	}

	public void setLiters(Double liters) {
		this.liters = liters;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		return "MilkProductionDetailDto [productionId=" + productionId + ", liters=" + liters + ", time=" + time + "]";
	}
	
}
