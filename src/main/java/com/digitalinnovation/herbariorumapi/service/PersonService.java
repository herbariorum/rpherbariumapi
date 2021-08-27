package com.digitalinnovation.herbariorumapi.service;

import com.digitalinnovation.herbariorumapi.dto.request.PersonDTO;
import com.digitalinnovation.herbariorumapi.dto.response.MessageResponseDTO;
import com.digitalinnovation.herbariorumapi.entity.Person;
import com.digitalinnovation.herbariorumapi.exception.PersonNotFoundException;
import com.digitalinnovation.herbariorumapi.mapper.PersonMapper;
import com.digitalinnovation.herbariorumapi.repository.PersonRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {
    // adiciona o repository
    private final PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);

        Person saved = personRepository.save(personToSave);
        return createMessageResponse(saved.getId(), "Created person with ID ");
    }


    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }


    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException{
        verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);

        Person updated = personRepository.save(personToUpdate);
        return createMessageResponse(updated.getId(), "Update person with ID ");
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException{
        return personRepository.findById(id).orElseThrow(()-> new PersonNotFoundException(id));

    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
