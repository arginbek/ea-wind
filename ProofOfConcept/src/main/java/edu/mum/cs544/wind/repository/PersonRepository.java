package edu.mum.cs544.wind.repository;

import edu.mum.cs544.wind.domain.Person;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface PersonRepository extends CrudRepository<Person, Long> {

}
