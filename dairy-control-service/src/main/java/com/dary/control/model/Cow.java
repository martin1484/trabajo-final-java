package com.dary.control.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name="cow")
public class Cow extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
    private Long id;
	
	@Column(name="ear_mark")
	private Long earMark;

	@Column(name="name")
    private String name;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cow")
    @Fetch(FetchMode.JOIN)
    private List<MilkProduction> productions;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
    @JoinColumn(name = "dary_producer_id")
    private DaryProducer daryProducer;

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

	public List<MilkProduction> getProductions() {
		return productions;
	}

	public void setProductions(List<MilkProduction> productions) {
		this.productions = productions;
	}

	public DaryProducer getDaryProducer() {
		return daryProducer;
	}

	public void setDaryProducer(DaryProducer daryProducer) {
		this.daryProducer = daryProducer;
	}
    
    
	
}
