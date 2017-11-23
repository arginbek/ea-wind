package edu.mum.cs544.wind.controller;

import edu.mum.cs544.wind.domain.Person;
import edu.mum.cs544.wind.domain.Role;
import edu.mum.cs544.wind.exception.UnauthorizedPersonException;
import edu.mum.cs544.wind.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping("/persons")
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public Person addPerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }
    
    @GetMapping("/persons/{id}")
    public Person getPerson(@PathVariable Long id, Principal principal) {
    	String username = principal.getName();
        Person person = personService.getPersonByUsername(username);
        
        if (!person.getRoles().contains(Role.ROLE_ADMIN) && person.getId() != id) {
        	throw new UnauthorizedPersonException("UNAUTHORIZED.");
        }
        
        return personService.getPerson(id);
    }

    @GetMapping("/persons")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @PutMapping("/persons/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person person, Principal principal) {
    	String username = principal.getName();
        Person personPersisted = personService.getPersonByUsername(username);
        
        if (!personPersisted.getRoles().contains(Role.ROLE_ADMIN)) {
        	if (personPersisted.getId() != id) {
        		throw new UnauthorizedPersonException("UNAUTHORIZED.");
        	}
        	
        	person.setRoles(personPersisted.getRoles());
        }
        
        return personService.updatePerson(person);
    }

    @DeleteMapping("/persons/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void removePerson(@PathVariable Long id) {
        personService.removePerson(id);
    }

}
