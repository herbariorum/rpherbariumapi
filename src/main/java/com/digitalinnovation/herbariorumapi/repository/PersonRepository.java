package com.digitalinnovation.herbariorumapi.repository;

import com.digitalinnovation.herbariorumapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
