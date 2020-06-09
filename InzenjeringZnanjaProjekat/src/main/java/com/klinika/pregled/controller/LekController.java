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

import com.klinika.pregled.cbrApplication.CBRModelLek;
import com.klinika.pregled.cbrApplication.CBRService;
import com.klinika.pregled.dto.CBRLekDTO;
import com.klinika.pregled.dto.LekDTO;
import com.klinika.pregled.dto.TestDTO;
import com.klinika.pregled.model.Lek;
import com.klinika.pregled.model.Pregled;
import com.klinika.pregled.model.Simptom;
import com.klinika.pregled.model.ZdravstveniKarton;
import com.klinika.pregled.model.Dijagnoza;
import com.klinika.pregled.repository.LekRepository;
import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;
import com.klinika.pregled.repository.DijagnozaRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/lek")
public class LekController {

	@Autowired
	private LekRepository lekRepository;
	
	@Autowired
	private DijagnozaRepository dijagnozaRepository;
	
	@Autowired
	private CBRService CBRService;
	
	@GetMapping
	public ResponseEntity<?> getLekovi(){
		List<Lek> lekovi = lekRepository.findAll();
		return new ResponseEntity<List<Lek>>(lekovi, HttpStatus.OK);
	}
	
	@GetMapping("/getAllDijagnoza")
	public ResponseEntity<?> getDijagnoze(){
		List<Dijagnoza> dijagnoze = dijagnozaRepository.findAll();
		return new ResponseEntity<List<Dijagnoza>>(dijagnoze, HttpStatus.OK);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<?> nadjiTerapiju(@RequestBody Set<Dijagnoza> dijagnoze, @PathVariable("id") Long id){
		List<String> cbrDijagnoze = new ArrayList<String>();
		for(Dijagnoza d : dijagnoze) {
			cbrDijagnoze.add(d.getName());
		}
		CBRModelLek newLekModel = new CBRModelLek();
		newLekModel.setDijagnoze(cbrDijagnoze);
		List<CBRLekDTO> listaPogodnih = CBRService.getLekMatches(newLekModel);
		return new ResponseEntity<List<CBRLekDTO>>(listaPogodnih, HttpStatus.OK);
		
	}
	
	@PostMapping("rbr/{id}")
	public ResponseEntity<?> nadjiTerapijuRBR(@RequestBody Set<Dijagnoza> dijagnoze, @PathVariable("id") Long id){
		JIPEngine engine = new JIPEngine();
    	engine.consultFile("data/terapija.pl");
		List<LekDTO> lekovi = new ArrayList<>();
		Set<String> nazivi = new HashSet<>();
		
		for(Dijagnoza d : dijagnoze) {
			String upit = d.getName();
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
						lekovi.add(new LekDTO(result));
						nazivi.add(result);
					}
				}
			}
		}
		
		return new ResponseEntity<List<LekDTO>>(lekovi, HttpStatus.OK);
	}
}
