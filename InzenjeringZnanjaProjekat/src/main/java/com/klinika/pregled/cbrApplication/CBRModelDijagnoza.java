package com.klinika.pregled.cbrApplication;

import java.util.ArrayList;
import java.util.List;

import com.klinika.pregled.dto.CBRTestDTO;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class CBRModelDijagnoza implements CaseComponent{

    //private List<Test> testovi = new ArrayList<Test>();
    private String dijagnoza;
    private List<String> testovi = new ArrayList<String>();

    @Override
    public Attribute getIdAttribute() {
        // TODO Auto-generated method stub
        return null;
    }
    public CBRModelDijagnoza() {
        super();
    }
    public List<String> getTestovi() {
        return testovi;
    }
    public void setTestovi(List<String> testovi) {
        this.testovi = testovi;
    }
    
    public void setTestovi2(List<CBRTestDTO> testovi) {
        for(CBRTestDTO test : testovi) {
        	this.testovi.add(test.getTest());
        }
    }
    
    @Override
    public String toString() {
        return "CBRModelDijagnoza [dijagnoze=" + dijagnoza + ", testovi=" + testovi + "]";
    }
    public String getDijagnoza() {
        return dijagnoza;
    }
    public void setDijagnoza(String dijagnoza) {
        this.dijagnoza = dijagnoza;
    }

}
