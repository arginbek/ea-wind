package edu.mum.cs544.wind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.cs544.wind.domain.Person;
import edu.mum.cs544.wind.domain.ROLE;
import edu.mum.cs544.wind.service.PersonService;


@RestController
public class PersonController {
	@Autowired
	private PersonService personService;

	@PostMapping("/persons")
	public Person addPerson(@RequestBody Person person) {
		person.addRole(ROLE.CUSTOMER);
		return personService.addPerson(person);
	}
	@GetMapping("/persons/{id}")
	public Person snippet(@PathVariable Long id){
//		assert personService != null;
		return personService.findPerson(id);
	}

}
