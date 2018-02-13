package com.mercadolibre.services;

import org.springframework.stereotype.Service;

import com.mercadolibre.exceptions.DnaExcepcion;
import com.mercadolibre.utils.CheckMatrixUtils;
import com.mercadolibre.utils.ComparatorUtils;

@Service
public class MutantService {

	public boolean isMutant(String[] dna) throws DnaExcepcion {
		int mutantDna = 0;

		try {
			CheckMatrixUtils.checkMatrix(dna);

			for (int i = 0; i < dna.length; i++) {
				for (int j = 0; j < dna[i].length(); j++) {

					// Chequeo combinaciones en forma horizontal
					if (j < dna[i].length() - 3) {

						if (ComparatorUtils.areEqualDna(dna[i].charAt(j), dna[i].charAt(j + 1), dna[i].charAt(j + 2),
								dna[i].charAt(j + 3))) {
							mutantDna++;
						}
					}

					// Chequeo combinaciones en forma Vertical
					if (i < dna[i].length() - 3) {
						if (ComparatorUtils.areEqualDna(dna[i].charAt(j), dna[i + 1].charAt(j), dna[i + 2].charAt(j),
								dna[i + 3].charAt(j))) {
							mutantDna++;
						}
					}

					// Chequeo combinaciones en forma Oblicua, Derecha a izquierda & Arriba a abajo
					if (j < dna[i].length() - 3 && i < dna[i].length() - 3) {
						if (ComparatorUtils.areEqualDna(dna[i].charAt(j), dna[i + 1].charAt(j + 1),
								dna[i + 2].charAt(j + 2), dna[i + 3].charAt(j + 3))) {
							mutantDna++;
						}
					}

					// Chequeo combinaciones en forma Oblicua, Izquierda a derecha & Abajo a arriba
					if (dna[i].length() > 3 && j < dna[i].length() - 3 && i > 2) {
						if (ComparatorUtils.areEqualDna(dna[i].charAt(j), dna[i - 1].charAt(j + 1),
								dna[i - 2].charAt(j + 2), dna[i - 3].charAt(j + 3))) {
							mutantDna++;
						}
					}

					if (mutantDna >= 2) {
						return true;
					}
				}

			}

		} catch (DnaExcepcion e) {
			throw e;
		}

		return false;
	}

}
