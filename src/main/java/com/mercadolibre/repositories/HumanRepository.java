package com.mercadolibre.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mercadolibre.entities.Person;

public interface HumanRepository extends CrudRepository<Person, String> {

}
