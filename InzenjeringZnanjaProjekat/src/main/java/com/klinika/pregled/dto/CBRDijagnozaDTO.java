package com.klinika.pregled.dto;

public class CBRDijagnozaDTO {
	private String dijagnoza;

	public String getDijagnoza() {
		return dijagnoza;
	}

	public void setDijagnoza(String dijagnoza) {
		this.dijagnoza = dijagnoza;
	}

	@Override
	public String toString() {
		return "CBRDijagnozaDTO [dijagnoza=" + dijagnoza + "]";
	}
	
	
}
