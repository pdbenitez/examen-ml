package com.mercadolibre.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeAction;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import com.mercadolibre.entities.Person;
import com.mercadolibre.entities.Stats;
import com.mercadolibre.exceptions.DnaExcepcion;
import com.mercadolibre.repositories.PersonRepository;
import com.mercadolibre.utils.CheckMatrixUtils;
import com.mercadolibre.utils.ComparatorUtils;

@Service
public class MutantService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AmazonDynamoDB amazonDynamoDB;

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

	public Stats getStats() {

		Map<String, AttributeValue> key = new HashMap<>();
		key.put("dna", new AttributeValue().withS("DNA"));
		GetItemResult result = amazonDynamoDB.getItem("DnaCounters", key);

		Stats stats;
		if (result.getItem() != null) {

			AttributeValue count_mutant_dna = result.getItem().get("count_mutant_dna");
			AttributeValue count_human_dna = result.getItem().get("count_human_dna");

			stats = new Stats(Integer.parseInt(count_mutant_dna.getN()), Integer.parseInt(count_human_dna.getN()));

		} else {
			stats = new Stats(0, 0);
		}
		return stats;
	}

	public boolean getMutantStatus(String[] dna) throws DnaExcepcion {

		String dnaParser = Arrays.toString(dna);
		Person person = personRepository.findOne(dnaParser);
		boolean mutantCondition;

		if (person != null) {
			mutantCondition = person.getIsMutant();
		} else {
			mutantCondition = this.isMutant(dna);
			this.saveDna(dnaParser, mutantCondition);
		}

		return mutantCondition;
	}

	private void saveDna(String dnaParser, boolean mutantCondition) {

		String counter;
		personRepository.save(new Person(dnaParser, mutantCondition));

		if (mutantCondition) {
			counter = "count_mutant_dna";
		} else {
			counter = "count_human_dna";
		}

		this.incrementCounter(counter);
	}

	private void incrementCounter(String counter) {

		Map<String, AttributeValue> key = new HashMap<>();
		key.put("dna", new AttributeValue().withS("DNA"));

		UpdateItemRequest updateItemRequest = new UpdateItemRequest().withTableName("DnaCounters").withKey(key)
				.addAttributeUpdatesEntry(counter, new AttributeValueUpdate().withValue(new AttributeValue().withN("1"))
						.withAction(AttributeAction.ADD));

		amazonDynamoDB.updateItem(updateItemRequest);

	}
}
