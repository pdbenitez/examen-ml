package com.mercadolibre.services;

import com.mercadolibre.utils.ComparatorUtils;

public class MutantService {

	public boolean isMutant(String[] dna) {
		int mutantDna = 0;

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
				if (j < dna[i].length() - 3) {
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

		return false;
	}

}
