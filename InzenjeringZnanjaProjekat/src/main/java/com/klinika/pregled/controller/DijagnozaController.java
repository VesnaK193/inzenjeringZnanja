package com.klinika.pregled.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/dijagnoza")
public class DijagnozaController {

	@GetMapping
	public String proba(){
		return "This works!";
	}
}
