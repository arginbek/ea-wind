package edu.mum.cs544.wind.service;

import edu.mum.cs544.wind.domain.Person;
import edu.mum.cs544.wind.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;

    @Override
    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public List<Person> getPersons() {
        return new ArrayList<>();
    }

}
