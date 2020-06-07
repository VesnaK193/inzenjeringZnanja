package com.klinika.pregled.cbrApplication;

import java.util.ArrayList;
import java.util.List;

import com.klinika.pregled.model.Simptom;
import com.klinika.pregled.model.Test;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class CBRModelTest implements CaseComponent{

	//private List<Test> testovi = new ArrayList<Test>();
	private String test;
	private List<String> simptomi = new ArrayList<String>();
	
	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}
	public CBRModelTest() {
		super();
	}
	public List<String> getSimptomi() {
		return simptomi;
	}
	public void setSimptomi(List<String> simptomi) {
		this.simptomi = simptomi;
	}
	@Override
	public String toString() {
		return "CBRModelTest [testovi=" + test + ", simptomi=" + simptomi + "]";
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	
}
