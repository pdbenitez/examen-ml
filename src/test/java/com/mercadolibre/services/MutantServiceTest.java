package com.mercadolibre.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.mercadolibre.exceptions.DnaExcepcion;

public class MutantServiceTest {

	MutantService mutantService = new MutantService();

	@Test
	public void checkMutantDnaFourByFourMatrixHorizontal() throws DnaExcepcion {
		String[] dna = { "AAAA", "CCCC", "GACT", "GACT" };
		assertTrue(mutantService.isMutant(dna));
	}

	@Test
	public void checkMutantDnaFiveByFiveMatrixHorizontal() throws DnaExcepcion {
		String[] dna = { "GACTA", "GGGGT", "GTTTT", "CGTAC", "TTGCA" };
		assertTrue(mutantService.isMutant(dna));
	}

	@Test
	public void checkMutantDnaSixBySixMatrixHorizontal() throws DnaExcepcion {
		String[] dna = { "GACTAC", "GACGTA", "AAGCTA", "GTGGGG", "GTACAG", "ATTTTA" };
		assertTrue(mutantService.isMutant(dna));
	}

	@Test
	public void checkMutantDnaFourByFourMatrixVertical() throws DnaExcepcion {
		String[] dna = { "GACT", "GACT", "GACT", "CTCT" };
		assertTrue(mutantService.isMutant(dna));
	}

	@Test
	public void checkMutantDnaFiveByFiveMatrixVertical() throws DnaExcepcion {
		String[] dna = { "AATAA", "CATGC", "TATCG", "CATAC", "CCGTC" };
		assertTrue(mutantService.isMutant(dna));
	}

	@Test
	public void checkMutantDnaSixBySixMatrixVertical() throws DnaExcepcion {
		String[] dna = { "GACTAC", "GACTAC", "GTCTAC", "ATGCGC", "GTTCAT", "ATGCGA" };
		assertTrue(mutantService.isMutant(dna));
	}

	@Test
	public void checkMutantDnaFourByFourMatrixOblique() throws DnaExcepcion {
		String[] dna = { "GACC", "AGCC", "GCGT", "CTCG" };
		assertTrue(mutantService.isMutant(dna));
	}

	@Test
	public void checkMutantDnaSixBySixMatrixMix() throws DnaExcepcion {
		String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCATGC" };
		assertTrue(mutantService.isMutant(dna));
	}

	@Test
	public void checkHumanDnaFourByFourMatrix() throws DnaExcepcion {
		String[] dna = { "ATGC", "CATC", "TAGT", "GTCT" };
		assertFalse(mutantService.isMutant(dna));
	}

	@Test
	public void checkHumanDnaFiveByFiveMatrix() throws DnaExcepcion {
		String[] dna = { "ATGCG", "CATCA", "TAGTT", "GTCTG", "GTACC" };
		assertFalse(mutantService.isMutant(dna));
	}

	@Test
	public void checkHumanDnaSixBySixMatrix() throws DnaExcepcion {
		String[] dna = { "ATGCGA", "CATCTA", "TAGTAT", "GTCTGG", "GTCACC", "ATTGCA" };
		assertFalse(mutantService.isMutant(dna));
	}

}
