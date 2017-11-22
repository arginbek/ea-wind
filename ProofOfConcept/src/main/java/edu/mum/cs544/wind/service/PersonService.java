package edu.mum.cs544.wind.service;

import edu.mum.cs544.wind.domain.Person;

public interface PersonService {
	Person addPerson(Person person);
	Person findPerson(long id);
}
