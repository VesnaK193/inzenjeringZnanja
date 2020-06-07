package com.klinika.pregled.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klinika.pregled.model.Dijagnoza;
import com.klinika.pregled.repository.DijagnozaRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/dijagnoza")
public class DijagnozaController {

	@Autowired
	private DijagnozaRepository dijagnozaRepo;

	@GetMapping
	public ResponseEntity<?> allDijagnoze(){
		List<Dijagnoza> dijagnoze = dijagnozaRepo.findAll();
		return new ResponseEntity<List<Dijagnoza>>(dijagnoze, HttpStatus.OK);
	}
}
