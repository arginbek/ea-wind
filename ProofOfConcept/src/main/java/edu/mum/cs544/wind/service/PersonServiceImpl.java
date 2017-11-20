package edu.mum.cs544.wind.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs544.wind.domain.Person;
import edu.mum.cs544.wind.repository.PersonRepository;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {
	@Autowired
	PersonRepository personRepository;

	@Override
	public Person addPerson(Person person) {
		return personRepository.save(person);
	}

}
