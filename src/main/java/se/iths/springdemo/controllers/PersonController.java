package se.iths.springdemo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.iths.springdemo.configurations.TestConfiguration;
import se.iths.springdemo.dtos.PersonDto;
import se.iths.springdemo.dtos.PersonEmail;
import se.iths.springdemo.services.Service;

import java.util.List;

@RestController
public class PersonController {

    Logger log = LoggerFactory.getLogger(PersonController.class);

    private TestConfiguration testConfiguration;

    private final Service service;

    public PersonController(TestConfiguration testConfiguration, Service service) {
        this.testConfiguration = testConfiguration;
        this.service = service;
    }


    @GetMapping("/persons/title/")
    public String title() {
        return testConfiguration.getFoo();
    }


    @GetMapping("/persons/search")
    public PersonDto search(@RequestParam String genre) {
        return new PersonDto(1L,"","");
    }


    @GetMapping("/persons")
    public List<PersonDto> all() {
        return service.getAllPersons();
    }

    @GetMapping("/persons/{id}")
    public PersonDto one(@PathVariable Long id) {
        log.info("For your information we got a request for person with id="+id);
        return service.getOne(id)
                .orElseThrow(() -> getStatusException(id));
    }

    private ResponseStatusException getStatusException(Long id) {
        log.error("Wrong id, " + id);
        return new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Id " + id + " not found.");
    }

    @PostMapping("/persons")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto create(@RequestBody PersonDto person) {
        log.info("Call to create with person " + person);
        return service.createPerson(person);
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
