package com.klinika.pregled.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klinika.pregled.cbrApplication.CBRModelDijagnoza;
import com.klinika.pregled.cbrApplication.CBRService;
import com.klinika.pregled.dto.CBRDijagnozaDTO;
import com.klinika.pregled.dto.DijagnozaDTO;
import com.klinika.pregled.model.Dijagnoza;
import com.klinika.pregled.model.Test;
import com.klinika.pregled.repository.DijagnozaRepository;
import com.klinika.pregled.repository.TestRepository;
import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/dijagnoza")
public class DijagnozaController {

	@Autowired
	private DijagnozaRepository dijagnozaRepo;
	
	@Autowired
	private TestRepository testRepo;
	
	@Autowired
	private CBRService CBRService;

	@GetMapping
	public ResponseEntity<?> getDijagnoze(){
		List<Dijagnoza> dijagnoze = dijagnozaRepo.findAll();
		return new ResponseEntity<List<Dijagnoza>>(dijagnoze, HttpStatus.OK);
	}
	
	@GetMapping("/getAllTestovi")
	public ResponseEntity<?> getTestovi(){
		List<Test> testovi = testRepo.findAll();
		return new ResponseEntity<List<Test>>(testovi, HttpStatus.OK);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<?> nadjiDijagnozu(@RequestBody Set<Test> testovi, @PathVariable("id") Long id){
		List<String> cbrTestovi = new ArrayList<String>();
		for(Test t : testovi) {
			cbrTestovi.add(t.getName());
			System.out.println("Jos jedan test upisan!");
		}
		CBRModelDijagnoza newDijagnozaModel = new CBRModelDijagnoza();
		newDijagnozaModel.setTestovi(cbrTestovi);
		List<CBRDijagnozaDTO> listaPogodnih = CBRService.getDijagnozaMatches(newDijagnozaModel);
		return new ResponseEntity<List<CBRDijagnozaDTO>>(listaPogodnih, HttpStatus.OK);
		
	}
	
	@PostMapping("rbr/{id}")
	public ResponseEntity<?> nadjiDijagnozuRBR(@RequestBody Set<Test> testovi, @PathVariable("id") Long id){
		JIPEngine engine = new JIPEngine();
    	engine.consultFile("data/dijagnoza.pl");
		List<DijagnozaDTO> dijagnoze = new ArrayList<>();
		Set<String> nazivi = new HashSet<>();
		
		for(Test t : testovi) {
			String upit = t.getName();
			upit = upit.replace(" ", "_");
	    	upit = upit.toLowerCase();
	    	
	    	JIPQuery query = engine.openSynchronousQuery("medicine_for_diagnosis(X," + upit +  ")");
	    	
	    	JIPTerm solution;
			while ( (solution = query.nextSolution()) != null) {
				System.out.println("solution: " + solution);
				
				for (JIPVariable var: solution.getVariables()) {
					System.out.println(var.getName() + "=" + var.getValue());
					
					String result = var.getValue().toString();
					result = result.replace("_", " ");
					result = result.replace("xthelinex", "-");
					
					result = result.substring(0, 1).toUpperCase() + result.substring(1).toLowerCase();
					if(!nazivi.contains(result)) {
						dijagnoze.add(new DijagnozaDTO(result));
						nazivi.add(result);
					}
				}
			}
		}
		
		return new ResponseEntity<List<DijagnozaDTO>>(dijagnoze, HttpStatus.OK);
	}
}

