package com.mercadolibre.entities;

import java.util.List;

public class Mutant {

	public Mutant(List<String> dna) {
		super();
		this.dna = dna;
	}

	public Mutant() {
	}

	private List<String> dna;

	public List<String> getDna() {
		return dna;
	}

	public void setDna(List<String> dna) {
		this.dna = dna;
	}
}
