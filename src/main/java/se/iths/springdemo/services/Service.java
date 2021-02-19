package se.iths.springdemo.services;

import se.iths.springdemo.dtos.PersonDto;
import se.iths.springdemo.dtos.PersonEmail;

import java.util.List;
import java.util.Optional;

public interface Service {
    List<PersonDto> getAllPersons();

    Optional<PersonDto> getOne(Long id);

    PersonDto createPerson(PersonDto person);

    void delete(Long id);

    PersonDto replace(Long id, PersonDto personDto);

    PersonDto update(Long id, PersonEmail personEmail);
}
