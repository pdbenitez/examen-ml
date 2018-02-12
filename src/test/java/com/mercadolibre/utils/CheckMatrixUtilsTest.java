package com.mercadolibre.utils;

import org.junit.Test;

import com.mercadolibre.exceptions.DnaExcepcion;
import com.mercadolibre.exceptions.InvalidDnaSequence;
import com.mercadolibre.exceptions.InvalidMatrixSize;

public class CheckMatrixUtilsTest {

	@Test(expected = InvalidMatrixSize.class)
	public void checkDnaInvalidMatrixSize() throws DnaExcepcion {
		String[] dna = { "ATGC", "CATC", "TAGT", "GTCTA" };
		CheckMatrixUtils.checkMatrix(dna);
	}

	@Test(expected = InvalidDnaSequence.class)
	public void checkDnaInvalidSequence() throws DnaExcepcion {
		String[] dna = { "QTGC", "CATC", "TAGT", "GTCT" };
		CheckMatrixUtils.checkMatrix(dna);
	}
}
