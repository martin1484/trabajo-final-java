package com.dary.control.dto;


public class CowDto extends BaseDto{

	private Long id;
	
	private Long earMark;

    private String name;
    
    private Long daryProducerId;

	public CowDto() {
		super();
	}

	public CowDto(Long earMark, String name) {
		super();
		this.earMark = earMark;
		this.name = name;
	}

	public CowDto(Long id, Long earMark, String name) {
		super();
		this.id = id;
		this.earMark = earMark;
		this.name = name;
	}

	public CowDto(Long id, Long earMark, String name, Long daryProducerId) {
		super();
		this.id = id;
		this.earMark = earMark;
		this.name = name;
		this.daryProducerId = daryProducerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEarMark() {
		return earMark;
	}

	public void setEarMark(Long earMark) {
		this.earMark = earMark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDaryProducerId() {
		return daryProducerId;
	}

	public void setDaryProducerId(Long daryProducerId) {
		this.daryProducerId = daryProducerId;
	}
  
    
    
}
