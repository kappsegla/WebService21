package se.iths.springdemo.controllers;

import se.iths.springdemo.dtos.PersonDto;
import se.iths.springdemo.dtos.PersonEmail;
import se.iths.springdemo.services.Service;

import java.util.List;
import java.util.Optional;

public class TestService implements Service {
    @Override
    public List<PersonDto> getAllPersons() {
        return List.of(new PersonDto(1,"Test","Test"), new PersonDto(2,"Test2","Test2"));
    }

    @Override
    public Optional<PersonDto> getOne(Long id) {
        if( id == 1)
            return Optional.of(new PersonDto(1,"Test","Test"));
        return Optional.empty();
    }

    @Override
    public PersonDto createPerson(PersonDto person) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public PersonDto replace(Long id, PersonDto personDto) {
        return null;
    }

    @Override
    public PersonDto update(Long id, PersonEmail personEmail) {
        return null;
    }
}
