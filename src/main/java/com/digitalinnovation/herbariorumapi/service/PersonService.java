package com.digitalinnovation.herbariorumapi.service;

import com.digitalinnovation.herbariorumapi.dto.request.PersonDTO;
import com.digitalinnovation.herbariorumapi.dto.response.MessageResponseDTO;
import com.digitalinnovation.herbariorumapi.entity.Person;
import com.digitalinnovation.herbariorumapi.exception.PersonNotFoundException;
import com.digitalinnovation.herbariorumapi.mapper.PersonMapper;
import com.digitalinnovation.herbariorumapi.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class PersonService {
    // adiciona o repository
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID "+savedPerson.getId())
                .build();
    }


    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Person verifyIfExists(Long id) throws PersonNotFoundException{
        return personRepository.findById(id).orElseThrow(()-> new PersonNotFoundException(id));

    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }
}
