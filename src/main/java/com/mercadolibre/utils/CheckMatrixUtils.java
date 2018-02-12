package com.mercadolibre.utils;

import com.mercadolibre.exceptions.DnaExcepcion;
import com.mercadolibre.exceptions.InvalidDnaSequence;
import com.mercadolibre.exceptions.InvalidMatrixSize;

public class CheckMatrixUtils {

	public static boolean checkMatrix(String[] dna) throws DnaExcepcion {

		int dnaSize = dna.length;
		String validStrings = "[ACGT]+";

		for (String dnaSequence : dna) {
			if (dnaSequence.length() != dnaSize) {
				throw new InvalidMatrixSize();
			}

			if (!dnaSequence.matches(validStrings)) {
				throw new InvalidDnaSequence();
			}

		}

		return true;
	}
}
