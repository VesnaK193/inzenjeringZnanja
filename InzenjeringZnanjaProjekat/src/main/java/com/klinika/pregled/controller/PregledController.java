package com.klinika.pregled.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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

import com.klinika.pregled.cbrApplication.CBRModelTest;
import com.klinika.pregled.cbrApplication.CBRService;
import com.klinika.pregled.dto.CBRSimptomDTO;
import com.klinika.pregled.dto.CBRTestDTO;
import com.klinika.pregled.model.Pacijent;
import com.klinika.pregled.model.Pregled;
import com.klinika.pregled.model.Simptom;
import com.klinika.pregled.model.ZdravstveniKarton;
import com.klinika.pregled.repository.PacijentRepository;
import com.klinika.pregled.repository.PregledRepository;
import com.klinika.pregled.repository.SimptomRepository;
import com.klinika.pregled.repository.ZdravstveniKartonRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/pregled")
public class PregledController {

	@Autowired
	private PregledRepository preglediRepo;
	
	@Autowired
	private PacijentRepository pacijentRepo;
	
	@Autowired
	private SimptomRepository simptomRepo;
	
	@Autowired
	private ZdravstveniKartonRepository zdrRepo;
	
	@Autowired
	private CBRService CBRService;
	
	@GetMapping
	public ResponseEntity<?> getPregledi(){
		List<Pregled> pregledi = preglediRepo.findAll();
		return new ResponseEntity<List<Pregled>>(pregledi, HttpStatus.OK);
	}
	
	@GetMapping("/pacijent")
	public ResponseEntity<?> getPacijent(){
		List<Pacijent> pacijent = pacijentRepo.findAll();
		return new ResponseEntity<List<Pacijent>>(pacijent, HttpStatus.OK);
	}
	
	@GetMapping("/simptom")
	public ResponseEntity<?> getSimptomi(){
		List<Simptom> simptomi = simptomRepo.findAll();
		return new ResponseEntity<List<Simptom>>(simptomi, HttpStatus.OK);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<?> dodajPregled(@RequestBody Pregled preg, @PathVariable("id") Long id){
		//Pacijent pacijent =  pacijentRepo.findById(p.getId()).get();
		//System.out.println(p.getKarton().getId());
		//System.out.println(p.getKarton().getPacijent().getName());
		ZdravstveniKarton karton = zdrRepo.getOne(id);
		Pregled newp = new Pregled();
		Set<Simptom> newS = new HashSet<>();
		Set<Simptom> simptomi = preg.getSimptomi();
		for(Simptom sim : simptomi) {
			Simptom s = new Simptom();
			s.setId(sim.getId());
			s.setName(sim.getName());
			newS.add(s);
		}
		newp.setSimptomi(newS);
		newp.setKarton(karton);
		Pregled newpreg = preglediRepo.save(newp);
		
		List<String> cbrSimptomi = new ArrayList<String>();
		for(Simptom s : newS) {
			cbrSimptomi.add(s.getName());
		}
		
		CBRModelTest newModel = new CBRModelTest();
		newModel.setSimptomi(cbrSimptomi);
		List<CBRTestDTO> listat = CBRService.getTestMatches(newModel);
		return new ResponseEntity<List<CBRTestDTO>>(listat, HttpStatus.OK);
	}
}
