package com.mercadolibre.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.entities.Mutant;
import com.mercadolibre.entities.Stats;
import com.mercadolibre.exceptions.DnaExcepcion;
import com.mercadolibre.services.MutantService;

@RestController
public class MutantController {

	@Autowired
	private MutantService mutantService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "/mutant/")
	@ResponseBody
	public ResponseEntity isMutant(@RequestBody Mutant mutante) throws DnaExcepcion {

		String[] dna = mutante.getDna().toArray((new String[0]));
		boolean isMutant = mutantService.getMutantStatus(dna);

		ResponseEntity response;

		if (isMutant) {
			response = new ResponseEntity(HttpStatus.OK);
		} else {
			response = new ResponseEntity(HttpStatus.FORBIDDEN);
		}
		return response;
	}

	@RequestMapping("/stats")
	public Stats getStats() {
		return mutantService.getStats();
	}

}
