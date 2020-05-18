package com.klinika.pregled.cbrApplication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klinika.pregled.dto.CBRResponseDTO;
import com.klinika.pregled.dto.CBRTestDTO;
import com.klinika.pregled.repository.PregledRepository;
//
//import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;
//import ucm.gaia.jcolibri.method.retrieve.selection.SelectCases;
import com.klinika.pregled.repository.TestRepository;

import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;
import ucm.gaia.jcolibri.method.retrieve.selection.SelectCases;

@Service
public class CBRService {

	@Autowired
	private PregledRepository repo;
	
	@Autowired
	private TestRepository testRepo;
	
	public List<CBRResponseDTO> getMatches(CBRModelPregled cbr){
		CbrApplication app = new CbrApplication(repo.findAll());
		
		Collection<RetrievalResult> eval = app.evaluate(cbr);
		eval = SelectCases.selectTopKRR(eval, 5);
		
		ArrayList<CBRResponseDTO> rezultati = new ArrayList<>();
		for(RetrievalResult r : eval) {
			if(r.getEval() > 0) {
				CBRResponseDTO novi = new CBRResponseDTO();
				novi.setDijagnoza(((CBRModelPregled)r.get_case().getDescription()).getDijagnoza());;
				//novi.setId(((CBRModelSimptomPregled)r.get_case().getDescription()).getId());
				novi.setSimptomi(((CBRModelPregled)r.get_case().getDescription()).getSimptom());
				rezultati.add(novi);
			}else {
				System.out.println("Nothing matches!");
			}
		}
//		for(CBRResponseDTO cb : rezultati) {
//			System.out.println(cb);
//		}
		return rezultati;
	}
	
	public List<CBRTestDTO> getTestMatches(CBRModelTest cbr){
		CBRApplicationTest app = new CBRApplicationTest(testRepo.findAll());
		
		Collection<RetrievalResult> eval = app.evaluate(cbr);
		eval = SelectCases.selectTopKRR(eval, 5);
		
		ArrayList<CBRTestDTO> rezultati = new ArrayList<>();
		for(RetrievalResult r : eval) {
			if(r.getEval() > 0) {
				CBRTestDTO novi = new CBRTestDTO();
				novi.setTest(((CBRModelTest)r.get_case().getDescription()).getTest());
				rezultati.add(novi);
			}else {
				System.out.println("Nothing matches!");
			}
		}
		return rezultati;
	}
}
