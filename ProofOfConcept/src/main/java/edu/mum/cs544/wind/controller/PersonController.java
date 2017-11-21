package edu.mum.cs544.wind.controller;

import edu.mum.cs544.wind.domain.Person;
import edu.mum.cs544.wind.domain.Role;
import edu.mum.cs544.wind.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping("/persons")
    public Person addPerson(@RequestBody Person person) {
        person.addRole(Role.CUSTOMER);
        return personService.addPerson(person);
    }

    @GetMapping("/persons")
    public List<Person> getPersons() {
        return personService.getPersons();
    }

}
