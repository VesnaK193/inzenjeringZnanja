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

@Entity
@Table(name = "pregled")
public class Pregled {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Dijagnoza dijagnoza;
	
	@ManyToMany//(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "pregled_simptomi", joinColumns = @JoinColumn(name = "pregled_id"), inverseJoinColumns = @JoinColumn(name = "simptom_id"))
	private Set<Simptom> simptomi = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ZdravstveniKarton karton;
	
//	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	private Set<Test> testovi = new HashSet<>();
	
//	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	private Pacijent pacijent;
	
//	public Pacijent getPacijent() {
//		return pacijent;
//	}
//
//	public void setPacijent(Pacijent pacijent) {
//		this.pacijent = pacijent;
//	}

	public Pregled() {
		super();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Dijagnoza getDijagnoza() {
		return dijagnoza;
	}
	public void setDijagnoza(Dijagnoza dijagnoza) {
		this.dijagnoza = dijagnoza;
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

//	public Set<Test> getTestovi() {
//		return testovi;
//	}
//
//	public void setTestovi(Set<Test> testovi) {
//		this.testovi = testovi;
//	}
}
