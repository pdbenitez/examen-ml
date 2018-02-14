package com.mercadolibre.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "PersonsDna")
public class Person {

	public Person() {
	}

	public Person(String dna) {
		super();
		this.dna = dna;
	}

	private String dna;

	@DynamoDBHashKey(attributeName = "Dna")
	public String getDna() {
		return dna;
	}

	public void setDna(String dna) {
		this.dna = dna;
	}
}
