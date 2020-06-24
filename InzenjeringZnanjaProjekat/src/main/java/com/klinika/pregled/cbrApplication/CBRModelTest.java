package com.klinika.pregled.cbrApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.klinika.pregled.model.Simptom;
import com.klinika.pregled.model.Test;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class CBRModelTest implements CaseComponent{

	private Set<String> testovi = new HashSet<>();
	private List<String> simptomi = new ArrayList<String>();
	
	@Override
	public Attribute getIdAttribute() {
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
	
	
	public Set<String> getTestovi() {
		return testovi;
	}

	public void setTestovi(Set<String> testovi) {
		this.testovi = testovi;
	}

	@Override
	public String toString() {
		return "CBRModelTest [testovi=" + testovi + ", simptomi=" + simptomi + "]";
	}
	
	
}
