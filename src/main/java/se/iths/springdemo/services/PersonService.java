package se.iths.springdemo.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.iths.springdemo.dtos.PersonDto;
import se.iths.springdemo.dtos.PersonEmail;
import se.iths.springdemo.entities.Person;
import se.iths.springdemo.mappers.PersonMapper;
import se.iths.springdemo.repositories.PersonRepsitory;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements se.iths.springdemo.services.Service {

    private final PersonMapper personMapper;
    private PersonRepsitory personRepsitory;

    public PersonService(PersonRepsitory personRepsitory, PersonMapper personMapper) {
        this.personRepsitory = personRepsitory;
        this.personMapper = personMapper;
    }

    @Override
    public List<PersonDto> getAllPersons() {
        return personMapper.mapp(personRepsitory.findAll());
    }

    @Override
    public Optional<PersonDto> getOne(Long id) {
        return personMapper.mapp(personRepsitory.findById(id));
    }

    @Override
    public PersonDto createPerson(PersonDto person) {
        if (person.getName().isEmpty())
            throw new RuntimeException();
        //Mapp from PersonDto to Person
        return personMapper.mapp(personRepsitory.save(personMapper.mapp(person)));
    }

    @Override
    public void delete(Long id) {
        personRepsitory.deleteById(id);
    }

    @Override
    public PersonDto replace(Long id, PersonDto personDto) {
        Optional<Person> person = personRepsitory.findById(id);
        if( person.isPresent())
        {
            Person updatedPerson = person.get();
            updatedPerson.setName(personDto.getName());
            updatedPerson.setEmail(personDto.getEmail());
            return personMapper.mapp(personRepsitory.save(updatedPerson));
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Id " + id + " not found.");
    }

    @Override
    public PersonDto update(Long id, PersonEmail personEmail) {
        Optional<Person> person = personRepsitory.findById(id);
        if( person.isPresent())
        {
            Person updatedPerson = person.get();
            if( personEmail.email != null)
                updatedPerson.setEmail(personEmail.email);
            return personMapper.mapp(personRepsitory.save(updatedPerson));
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Id " + id + " not found.");
    }
}
