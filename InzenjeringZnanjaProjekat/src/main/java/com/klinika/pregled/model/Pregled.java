package com.klinika.pregled.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pregled")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pregled {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToMany//(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "pregled_simptomi", joinColumns = @JoinColumn(name = "pregled_id"), inverseJoinColumns = @JoinColumn(name = "simptom_id"))
	private Set<Simptom> simptomi = new HashSet<>();
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private ZdravstveniKarton karton;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "pregled_testovi", joinColumns = @JoinColumn(name = "pregled_id"), inverseJoinColumns = @JoinColumn(name = "test_id"))
	private Set<Test> testovi = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "pregled_dijagnoza", joinColumns = @JoinColumn(name = "pregled_id"), inverseJoinColumns = @JoinColumn(name = "dijagnoza_id"))
	private Set<Dijagnoza> dijagnoze = new HashSet<>();
	

	public Pregled() {
		super();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public Set<Simptom> getSimptomi() {
		return simptomi;
	}
	public void setSimptomi(Set<Simptom> simptomi) {
		this.simptomi = simptomi;
	}

	public ZdravstveniKarton getKarton() {
		return karton;
	}

	public void setKarton(ZdravstveniKarton karton) {
		this.karton = karton;
	}

	public Set<Test> getTestovi() {
		return testovi;
	}

	public void setTestovi(Set<Test> testovi) {
		this.testovi = testovi;
	}

	public Set<Dijagnoza> getDijagnoze() {
		return dijagnoze;
	}

	public void setDijagnoze(Set<Dijagnoza> dijagnoze) {
		this.dijagnoze = dijagnoze;
	}


	public Set<Lek> getLekovi() {
		return lekovi;
	}

	public void setLekovi(Set<Lek> lekovi) {
		this.lekovi = lekovi;
	}

	
	
}
