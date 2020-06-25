package com.klinika.pregled.dto;

public class LekDTO {
	
	private Long id;
	private String lek;
	
	public LekDTO() {
		// TODO Auto-generated constructor stub
	}
	public LekDTO(String lek) {
		// TODO Auto-generated constructor stub
		this.lek = lek;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
