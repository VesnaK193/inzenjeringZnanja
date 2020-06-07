package com.klinika.pregled.dto;

public class TestDTO {

	private String test;
	
	public TestDTO() {
		// TODO Auto-generated constructor stub
	}
	public TestDTO(String test) {
		// TODO Auto-generated constructor stub
		this.test = test;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	@Override
	public String toString() {
		return "CBRTestDTO [test=" + test + "]";
	}
}
