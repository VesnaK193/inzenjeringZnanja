package com.klinika.pregled.cbrApplication;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.klinika.pregled.model.Simptom;
import com.klinika.pregled.model.Test;

import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CaseBaseFilter;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.InitializingException;

public class CsvConnectorTest implements Connector{

	private List<Test> testovi = new ArrayList<Test>();
	
	public CsvConnectorTest(List<Test> test) {
		// TODO Auto-generated constructor stub
		this.testovi = test;
	}
	
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCases(Collection<CBRCase> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initFromXMLfile(URL arg0) throws InitializingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<CBRCase> retrieveAllCases() {
		// TODO Auto-generated method stub
		LinkedList<CBRCase> cases = new LinkedList<CBRCase>();
		List<CBRModelTest> modelTestovi = new ArrayList<>();
		
		for(Test t : this.testovi) {
			CBRModelTest modelTest = new CBRModelTest();
			modelTest.setTest(t.getName());
			for(Simptom s : t.getSimptom()) {
				modelTest.getSimptomi().add(s.getName());
			}
			CBRCase cbrCase = new CBRCase();
			cbrCase.setDescription(modelTest);
			cases.add(cbrCase);
		}
		
		return cases;
	}

	@Override
	public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeCases(Collection<CBRCase> arg0) {
		// TODO Auto-generated method stub
		
	}

}
