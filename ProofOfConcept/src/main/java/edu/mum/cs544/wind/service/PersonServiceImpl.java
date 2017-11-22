package edu.mum.cs544.wind.service;

import edu.mum.cs544.wind.domain.Person;
import edu.mum.cs544.wind.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepository personRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

    @Override
    public Person addPerson(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

    @Override
    public List<Person> getPersons() {
        return (List<Person>) personRepository.findAll();
    }

    @Override
    public Person getPerson(long id) {
        return personRepository.findOne(id);
    }

}
