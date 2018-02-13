package com.mercadolibre.entities;

public class Stats {

	public Stats(double count_mutant_dna, double count_human_dna) {
		super();
		this.count_mutant_dna = (int) count_mutant_dna;
		this.count_human_dna = (int) count_human_dna;
		this.ratio = count_mutant_dna / count_human_dna;
	}

	private Integer count_mutant_dna;
	private Integer count_human_dna;
	private double ratio;

	public Integer getCount_mutant_dna() {
		return count_mutant_dna;
	}

	public void setCount_mutant_dna(Integer count_mutant_dna) {
		this.count_mutant_dna = count_mutant_dna;
	}

	public Integer getCount_human_dna() {
		return count_human_dna;
	}

	public void setCount_human_dna(Integer count_human_dna) {
		this.count_human_dna = count_human_dna;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
}
