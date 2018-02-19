package com.mercadolibre.entities;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StatsTest {

	@Test
	public void testStatsWithNoData() {

		Stats stats = new Stats(0, 0);
		assertTrue(stats.getCount_human_dna() == 0.0);
		assertTrue(stats.getCount_mutant_dna() == 0.0);
		assertTrue(stats.getRatio() == 0.0);
	}

	@Test
	public void testStatsWithNoDataHuman() {

		Stats stats = new Stats(1, 0);
		assertTrue(stats.getCount_human_dna() == 0.0);
		assertTrue(stats.getCount_mutant_dna() == 1.0);
		assertTrue(stats.getRatio() == 1.0);
	}

	@Test
	public void testStatsWithData() {

		Stats stats = new Stats(40, 100);
		assertTrue(stats.getCount_human_dna() == 100.0);
		assertTrue(stats.getCount_mutant_dna() == 40.0);
		assertTrue(stats.getRatio() == 0.4);
	}

}
