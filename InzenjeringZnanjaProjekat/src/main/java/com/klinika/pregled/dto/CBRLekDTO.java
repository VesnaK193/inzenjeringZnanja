package com.klinika.pregled.dto;

public class CBRLekDTO {
	private String lek;
	
	public CBRLekDTO() {
		// TODO Auto-generated constructor stub
		super();
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
