package edu.mum.cs544.wind.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import edu.mum.cs544.wind.domain.Person;

@Transactional
public interface PersonRepository extends CrudRepository<Person, Long> {

}
