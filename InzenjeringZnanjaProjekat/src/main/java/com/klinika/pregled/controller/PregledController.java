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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klinika.pregled.cbrApplication.CBRModelTest;
import com.klinika.pregled.cbrApplication.CBRService;
import com.klinika.pregled.dto.CBRSimptomDTO;
import com.klinika.pregled.dto.CBRTestDTO;
import com.klinika.pregled.dto.SimptomiDTO;
import com.klinika.pregled.dto.TestDTO;
import com.klinika.pregled.model.Pacijent;
import com.klinika.pregled.model.Pregled;
import com.klinika.pregled.model.Simptom;
import com.klinika.pregled.model.Test;
import com.klinika.pregled.model.ZdravstveniKarton;
import com.klinika.pregled.repository.PacijentRepository;
import com.klinika.pregled.repository.PregledRepository;
import com.klinika.pregled.repository.SimptomRepository;
import com.klinika.pregled.repository.TestRepository;
import com.klinika.pregled.repository.ZdravstveniKartonRepository;
import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;

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
	private TestRepository testTepo;
	
	@Autowired
	private CBRService CBRService;
	
	@GetMapping
	public ResponseEntity<?> getPregledi(){
		List<Pregled> pregledi = preglediRepo.findAll();
		return new ResponseEntity<List<Pregled>>(pregledi, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<?> createPregled(@RequestBody Long id){		//ID Zdravstvenog Kartona
		ZdravstveniKarton k = zdrRepo.getOne(id);
		Pregled newPregled = new Pregled();
		newPregled.setKarton(k);
		Pregled saved = this.preglediRepo.save(newPregled);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/karton/{id}")
	public ResponseEntity<?> getZdravstveniKarton(@PathVariable("id") Long id){
		ZdravstveniKarton k = zdrRepo.getOne(id);
		return new ResponseEntity<ZdravstveniKarton>(k, HttpStatus.OK);
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
	
	@PostMapping("karton/{id}")
	public ResponseEntity<?> createKarton(@PathVariable Long id) {
		Pacijent pacijent = pacijentRepo.findById(id).get();
		ZdravstveniKarton newKarton = new ZdravstveniKarton();
		newKarton.setPacijent(pacijent);
		ZdravstveniKarton karton = zdrRepo.save(newKarton);
		return new ResponseEntity<>(karton, HttpStatus.OK);
	}
	
	@PostMapping("/test/{id}")
	public ResponseEntity<?> saveTestove(@RequestBody Set<TestDTO> testovi, @PathVariable("id") Long id){
		
		Set<Test> newTestovi = new HashSet<>();
		Pregled pregled = preglediRepo.getOne(id);
		
		for(TestDTO t : testovi) {
			Test newTest = this.findOneTest(t.getName());
			System.out.println("***************************************************************");
			System.out.println("Selektovani test je : " + newTest.getName());
			System.out.println("***************************************************************");
			newTestovi.add(newTest);
		}
		pregled.setTestovi(newTestovi);
		Pregled savedPregled = preglediRepo.save(pregled);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("cbr/{id}")
	public ResponseEntity<?> findTestoveCBR(@RequestBody Set<SimptomiDTO> simptomi, @PathVariable("id") Long id){	//id Pregleda
		
		Pregled pregled = preglediRepo.getOne(id);
		
		Set<Simptom> newSimptomi = new HashSet<>();
		
		for(SimptomiDTO s : simptomi) {
			Simptom newSimptom = new Simptom(s.getName());
			newSimptom.setId(s.getId());
			newSimptomi.add(newSimptom);
		}
		pregled.setSimptomi(newSimptomi);
		
		List<String> cbrSimptomi = new ArrayList<String>();
		for(Simptom s : newSimptomi) {
			cbrSimptomi.add(s.getName());
		}
		
		CBRModelTest newModel = new CBRModelTest();
		newModel.setSimptomi(cbrSimptomi);
		List<TestDTO> listat = CBRService.getTestMatches(newModel);
		
		Pregled saved = preglediRepo.save(pregled);
		
		return new ResponseEntity<List<TestDTO>>(listat, HttpStatus.OK);
	}
	
	@PostMapping("rbr/{id}")
	public ResponseEntity<?> findTestove(@RequestBody Set<SimptomiDTO> simptomi, @PathVariable("id") Long id){
		
		Pregled pregled = preglediRepo.getOne(id);
		
		Set<String> nazivi = new HashSet<>();
		List<TestDTO> testovi = new ArrayList<>();
		
		JIPEngine engine = new JIPEngine();
    	engine.consultFile("data/program.pl");
  
		Set<Simptom> newSimptomi = new HashSet<>();
		
		for(SimptomiDTO s : simptomi) {
			Simptom newSimptom = new Simptom(s.getName());
			newSimptom.setId(s.getId());
			newSimptomi.add(newSimptom);
		}
		pregled.setSimptomi(newSimptomi);
		Pregled saved = preglediRepo.save(pregled);
		
		for(Simptom sim : newSimptomi) {
			String upit = sim.getName();
			upit = upit.replace(" ", "_");
	    	upit = upit.toLowerCase();
	    	
	    	JIPQuery query = engine.openSynchronousQuery("procedure_for_symptoms(X," + upit +  ")");
	    	
	    	JIPTerm solution;
			while ( (solution = query.nextSolution()) != null) {
				System.out.println("solution: " + solution);
				
				for (JIPVariable var: solution.getVariables()) {
					System.out.println(var.getName() + "=" + var.getValue());
					
					String result = var.getValue().toString();
					result = result.replace("_", " ");
					
					result = result.substring(0, 1).toUpperCase() + result.substring(1).toLowerCase();
					if(!nazivi.contains(result)) {
						testovi.add(new TestDTO(result));
						nazivi.add(result);
					}
				}
			}
		}
		
		return new ResponseEntity<List<TestDTO>>(testovi, HttpStatus.OK);
	}
	
	private Test findOneTest(String name) {
		List<Test> tests = testTepo.findAll();
		for(Test t : tests) {
			if(t.getName().equals(name)) {
				return t;
			}
		}
		return null;
	}
}
