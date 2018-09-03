package com.mavenworkforce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mavenworkforce.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	

}
