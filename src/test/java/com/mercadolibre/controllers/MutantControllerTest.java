package com.mercadolibre.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mercadolibre.entities.Person;
import com.mercadolibre.repositories.PersonRepository;
import com.mercadolibre.services.MutantService;

public class MutantControllerTest {

	private MockMvc mockMvc;

	@Mock
	private MutantService mutantService;

	@Mock
	private PersonRepository personRepository;

	@InjectMocks
	private MutantController mutantController;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(mutantController).build();
		personRepository.deleteAll();
		personRepository.save(new Person("A", false));

	}

	@Test
	public void controllerInitializedCorrectly() {
		assertThat(mutantController).isNotNull();

	}

	@Test
	public void getStatsStatusOK() throws Exception {
		mockMvc.perform(get("/stats")).andExpect(status().isOk());

	}

	@Test
	public void postMutantStatusForbiddenIsHuman() throws Exception {

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/mutant/")
				.content("{\"dna\":[\"ACGT\",\"TGCA\",\"GCAT\",\"TACG\"]}").contentType(MediaType.APPLICATION_JSON);
		this.mockMvc.perform(builder).andExpect(status().isForbidden());

	}

}
