package com.mavenworkforce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mavenworkforce.model.Person;
import com.mavenworkforce.repository.PersonRepository;

@RestController
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@GetMapping("/persons")
	public List<Person> getAllPerson() {
		return personRepository.findAll();
	}

	@GetMapping("/persons/{id}")
	public Person getPerson(@PathVariable long id) {
		return personRepository.findById(id).get();
	}

	@PostMapping("/persons")
	public ResponseEntity<Person> addPerson(@RequestBody Person person) {
		Person per = personRepository.save(person);
		if (per == null) {
			return new ResponseEntity<Person>(per, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Person>(per, HttpStatus.OK);
	}

	@PutMapping("/persons/{id}")
	public ResponseEntity<Void> updatePerson(@Valid @RequestBody Person person, @PathVariable Long id) {
		if (personRepository.save(person) == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("/persons/{id}")
	public ResponseEntity<Void> deletePerson(@PathVariable long id) {
		Person per = personRepository.findById(id).get();
		if (per.equals(null)) {
			personRepository.delete(per);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

		}

	}

}
