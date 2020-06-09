package com.klinika.pregled.dto;

public class LekDTO {
	
	private String lek;
	
	public LekDTO() {
		// TODO Auto-generated constructor stub
	}
	public LekDTO(String lek) {
		// TODO Auto-generated constructor stub
		this.lek = lek;
	}
	public String getLek() {
		return lek;
	}
	public void setLek(String lek) {
		this.lek = lek;
	}

	@Override
	public String toString() {
		return "CBRLekDTO [lek=" + lek + "]";
	}
}
