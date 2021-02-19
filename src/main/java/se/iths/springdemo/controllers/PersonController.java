package se.iths.springdemo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.iths.springdemo.dtos.PersonDto;
import se.iths.springdemo.dtos.PersonEmail;
import se.iths.springdemo.services.PersonService;

import java.util.List;

@RestController
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    public List<PersonDto> all() {
        return personService.getAllPersons();
    }

    @GetMapping("/persons/{id}")
    public PersonDto one(@PathVariable Long id) {
        return personService.getOne(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Id " + id + " not found."));
    }

    @PostMapping("/persons")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto create(@RequestBody PersonDto person){
        return personService.createPerson(person);
    }


    @DeleteMapping("/persons/{id}")
    public void delete(@PathVariable Long id){
        personService.delete(id);
    }

    @PutMapping("/persons/{id}")
    public PersonDto replace(@RequestBody PersonDto personDto, @PathVariable Long id) {
       return personService.replace(id, personDto);
    }

    @PatchMapping("/persons/{id}")
    public PersonDto update(@RequestBody PersonEmail personEmail, @PathVariable Long id) {
        return personService.update(id, personEmail);
    }
}
