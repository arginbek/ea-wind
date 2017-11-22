package edu.mum.cs544.wind.service;

import edu.mum.cs544.wind.domain.Person;

import java.util.List;

public interface PersonService {
    Person addPerson(Person person);

    List<Person> getAllPersons();

    Person getPerson(long id);

    Person getPersonByUsername(String username);

    Person updatePerson(Person person);

    void removePerson(Long id);
}
