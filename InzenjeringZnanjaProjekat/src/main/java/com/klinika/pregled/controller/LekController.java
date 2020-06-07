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
import com.klinika.pregled.model.Lek;
import com.klinika.pregled.model.Pregled;
import com.klinika.pregled.model.Simptom;
import com.klinika.pregled.model.ZdravstveniKarton;
import com.klinika.pregled.model.Dijagnoza;
import com.klinika.pregled.repository.LekRepository;
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
}
