package com.klinika.pregled.cbrApplication;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.klinika.pregled.model.Lek;
import com.klinika.pregled.model.Dijagnoza;
import com.klinika.pregled.model.Test;

import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CaseBaseFilter;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.InitializingException;

public class CsvConnectorLek implements Connector{
	private List<Lek> lekovi = new ArrayList<Lek>();
	
	public CsvConnectorLek(List<Lek> lekovi) {
		// TODO Auto-generated constructor stub
		this.lekovi = lekovi;
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
		List<CBRModelLek> modelLekovi= new ArrayList<>();
		
		for(Lek l : this.lekovi) {
			CBRModelLek modelLek = new CBRModelLek();
			modelLek.setLek(l.getName());
			for(Dijagnoza d : l.getDijagnoze()) {
				modelLek.getDijagnoze().add(d.getName());
			}
			CBRCase cbrCase = new CBRCase();
			cbrCase.setDescription(modelLek);
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
