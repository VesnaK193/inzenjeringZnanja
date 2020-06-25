package com.klinika.pregled.dto;

public class DijagnozaDTO {
	
	private Long id;
	private String name;
	
	
	public DijagnozaDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DijagnozaDTO(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 

	@Override
	public String toString() {
		return "DijagnozaDTO [dijagnoza=" + name + "]";
	}
	
	
	
	
}
