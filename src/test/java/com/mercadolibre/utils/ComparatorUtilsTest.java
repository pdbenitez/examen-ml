package com.mercadolibre.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ComparatorUtilsTest {

	@Test
	public void compareEqualDna() {
		assertTrue(ComparatorUtils.areEqualDna('a', 'a', 'a', 'a'));
	}

	@Test
	public void compareAllDistinctDna() {
		assertFalse(ComparatorUtils.areEqualDna('a', 'b', 'c', 'd'));
	}
	
	@Test
	public void compareTwoDistinctDnaTwoEqual() {
		assertFalse(ComparatorUtils.areEqualDna('a', 'a', 'b', 'b'));
	}
	
	@Test
	public void compareTwoDistinctThreeEqualDna() {
		assertFalse(ComparatorUtils.areEqualDna('a', 'a', 'a', 'b'));
	}

}
