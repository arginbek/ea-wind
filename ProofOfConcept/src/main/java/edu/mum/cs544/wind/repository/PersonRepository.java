package edu.mum.cs544.wind.repository;

import edu.mum.cs544.wind.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

@Transactional
public interface PersonRepository extends CrudRepository<Person, Long> {

    Person getPersonByUsername(@Param("username") String username);

}
