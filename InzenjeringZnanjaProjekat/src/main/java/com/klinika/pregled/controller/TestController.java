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
import com.klinika.pregled.cbrApplication.CBRModelTest;
import com.klinika.pregled.cbrApplication.CBRService;
import com.klinika.pregled.cbrApplication.CBRServiceDijagnoza;
import com.klinika.pregled.dto.CBRTestDTO;
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
//
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private TestRepository testoviRepo;
    
    @Autowired
    private PacijentRepository pacijentRepo;
    
    @Autowired
    private ZdravstveniKartonRepository zdrRepo;
    
    @Autowired
	private CBRServiceDijagnoza cBRServiceDjagnoza;
    
    @Autowired
	private CBRService CBRService;
    
    @Autowired
	private SimptomRepository simptomRepo;
    
    @Autowired
	private PregledRepository preglediRepo;

    @GetMapping("/getAllTestovi")
    public ResponseEntity<?> getTestovi(){
        List<Test> testovi = testoviRepo.findAll();
        return new ResponseEntity<List<Test>>(testovi, HttpStatus.OK);
    }
    
    @GetMapping("/pacijent")
	public ResponseEntity<?> getPacijent(){
		List<Pacijent> pacijent = pacijentRepo.findAll();
		return new ResponseEntity<List<Pacijent>>(pacijent, HttpStatus.OK);
	}
	
    /*@PostMapping("/dijagnoza")
	public ResponseEntity<?> dodajTest(@RequestBody Pregled pregled){
		
    	
    	ArrayList<String> listat = new ArrayList<String>();
		
		CBRModelDijagnoza newModelDijagnoza = new CBRModelDijagnoza();
		newModelDijagnoza.setTestovi(listat);
		
		List<CBRTestDTO> listaDijagnoza = cBRServiceDjagnoza.getDijagnozaMatches(newModelDijagnoza);
		
		return new ResponseEntity<List<CBRTestDTO>>(listaDijagnoza, HttpStatus.OK);
	}*/
}