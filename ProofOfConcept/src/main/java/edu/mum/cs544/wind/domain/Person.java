package edu.mum.cs544.wind.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Cascade;

@Entity
public class Person {
	@Id
	@GeneratedValue
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;

	@ElementCollection(targetClass = ROLE.class)
	@CollectionTable
	@Enumerated(EnumType.STRING)
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	private List<ROLE> roles;

	public Person() {
		roles = new ArrayList<>();
	}

	public Person(String firstName, String lastName, String email, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		roles = new ArrayList<>();
		roles.add(ROLE.CUSTOMER);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ROLE> getRoles() {
		return roles;
	}

	public void setRoles(List<ROLE> roles) {
		this.roles = roles;
	}
	
	public void addRole(ROLE role) {
		roles.add(role);
	}

}
