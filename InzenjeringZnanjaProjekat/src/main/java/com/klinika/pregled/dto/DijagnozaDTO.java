package com.klinika.pregled.dto;

public class DijagnozaDTO {
	
	private String dijagnoza;
	
	
	public DijagnozaDTO() {
		super();
	}
	

	public DijagnozaDTO(String dijagnoza) {
		super();
		this.dijagnoza = dijagnoza;
	}

	public String getDijagnoza() {
		return dijagnoza;
	}

	public void setDijagnoza(String dijagnoza) {
		this.dijagnoza = dijagnoza;
	}


	@Override
	public String toString() {
		return "DijagnozaDTO [dijagnoza=" + dijagnoza + "]";
	}
	
	
	
	
}
