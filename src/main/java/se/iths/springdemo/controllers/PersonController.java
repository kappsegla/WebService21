package se.iths.springdemo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.iths.springdemo.dtos.PersonDto;
import se.iths.springdemo.dtos.PersonEmail;
import se.iths.springdemo.services.Service;

import java.util.List;

@RestController
public class PersonController {

    private Service service;

    public PersonController(Service service) {
        this.service = service;
    }

    @GetMapping("/persons")
    public List<PersonDto> all() {
        return service.getAllPersons();
    }

    @GetMapping("/persons/{id}")
    public PersonDto one(@PathVariable Long id) {
        return service.getOne(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Id " + id + " not found."));
    }

    @PostMapping("/persons")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto create(@RequestBody PersonDto person){
        return service.createPerson(person);
    }


    @DeleteMapping("/persons/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("/persons/{id}")
    public PersonDto replace(@RequestBody PersonDto personDto, @PathVariable Long id) {
       return service.replace(id, personDto);
    }

    @PatchMapping("/persons/{id}")
    public PersonDto update(@RequestBody PersonEmail personEmail, @PathVariable Long id) {
        return service.update(id, personEmail);
    }
}
