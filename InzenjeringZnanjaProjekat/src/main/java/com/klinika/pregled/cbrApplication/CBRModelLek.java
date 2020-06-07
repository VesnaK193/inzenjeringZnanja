package com.klinika.pregled.cbrApplication;

import java.util.ArrayList;
import java.util.List;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class CBRModelLek implements CaseComponent{
	
	private String lek;
	private List<String> dijagnoze = new ArrayList<String>();

	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public CBRModelLek() {
		super();
	}

	public String getLek() {
		return lek;
	}

	public void setLek(String lek) {
		this.lek = lek;
	}

	public List<String> getDijagnoze() {
		return dijagnoze;
	}

	public void setDijagnoze(List<String> dijagnoze) {
		this.dijagnoze = dijagnoze;
	}
	
	@Override
	public String toString() {
		return "CBRModelLek [lekovi=" + lek + ", dijagnoze=" + dijagnoze + "]";
	}
}
