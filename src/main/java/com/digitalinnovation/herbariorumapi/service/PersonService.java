package com.digitalinnovation.herbariorumapi.service;

import com.digitalinnovation.herbariorumapi.dto.MessageResponseDTO;
import com.digitalinnovation.herbariorumapi.entity.Person;
import com.digitalinnovation.herbariorumapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(@RequestBody Person person){
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID "+savedPerson.getId())
                .build();
    }
}
