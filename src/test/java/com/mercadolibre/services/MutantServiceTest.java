package com.mercadolibre.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MutantServiceTest {

	MutantService mutantService = new MutantService();

	@Test
	public void checkMutantDnaFourByFourMatrixHorizontal() {
		String[] dna = { "AAAA", "CCCC", "GACT", "GACT" };
		assertTrue(mutantService.isMutant(dna));
	}

	@Test
	public void checkMutantDnaFiveByFiveMatrixHorizontal() {
		String[] dna = { "GACTA", "GGGGT", "GTTTT", "CGTAC", "TTGCAA" };
		assertTrue(mutantService.isMutant(dna));
	}

	@Test
	public void checkMutantDnaSixBySixMatrixHorizontal() {
		String[] dna = { "GACTAC", "GACGTA", "AAGCTA", "GTGGGG", "GTACAG", "ATTTTA" };
		assertTrue(mutantService.isMutant(dna));
	}

	@Test
	public void checkMutantDnaFourByFourMatrixVertical() {
		String[] dna = { "GACT", "GACT", "GACT", "CTCT" };
		assertTrue(mutantService.isMutant(dna));
	}

	@Test
	public void checkMutantDnaFiveByFiveMatrixVertical() {
		String[] dna = { "AATAA", "CATGC", "TATCG", "CATAC", "CCGTC" };
		assertTrue(mutantService.isMutant(dna));
	}

	@Test
	public void checkMutantDnaSixBySixMatrixVertical() {
		String[] dna = { "GACTAC", "GACTAC", "GTCTAC", "ATGCGC", "GTTCAT", "ATGCGA" };
		assertTrue(mutantService.isMutant(dna));
	}

	@Test
	public void checkMutantDnaFourByFourMatrixOblique() {
		String[] dna = { "GACC", "AGCC", "GCGT", "CTCG" };
		assertTrue(mutantService.isMutant(dna));
	}

	@Test
	public void checkMutantDnaSixBySixMatrixMix() {
		String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCATGC" };
		assertTrue(mutantService.isMutant(dna));
	}
	
	@Test
	public void checkHumanDnaFourByFourMatrixHorizontal() {
		String[] dna = { "ATGC", "CATC", "TAGT", "GTCT" };
		assertFalse(mutantService.isMutant(dna));
	}
	
	@Test
	public void checkHumanDnaFiveByFiveMatrixHorizontal() {
		String[] dna = { "ATGCG", "CATCA", "TAGTT", "GTCTG", "GTACC" };
		assertFalse(mutantService.isMutant(dna));
	}
	
	@Test
	public void checkHumanDnaSizBySixMatrixHorizontal() {
		String[] dna = { "ATGCGA", "CATCTA", "TAGTAT", "GTCTGG", "GTCACC", "ATTGCA" };
		assertFalse(mutantService.isMutant(dna));
	}

}
