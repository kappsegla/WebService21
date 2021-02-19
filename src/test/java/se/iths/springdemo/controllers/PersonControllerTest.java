package se.iths.springdemo.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import se.iths.springdemo.services.PersonService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PersonControllerTest {

    //Unit tests. Testing one thing at a time, in isolation.

    @Test
    void callingOneWithValidIdReturnsOnePerson(){
        PersonController personController = new PersonController(new TestService());

        var person = personController.one(1L);

        //AssertJ fluent assertions
        assertThat(person.getId()).isEqualTo(1);
        assertThat(person.getName()).isEqualTo("Test");
        assertThat(person.getEmail()).isEqualTo("Test");
    }

    @Test
    void callingOneWithInvalidIdThrowsExceptionWithResponseStatus404(){
        PersonController personController = new PersonController(new TestService());

        var exception = assertThrows(ResponseStatusException.class, () -> personController.one(2L) );
        assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }



}