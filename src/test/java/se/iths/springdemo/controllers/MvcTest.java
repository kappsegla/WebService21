package se.iths.springdemo.controllers;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import se.iths.springdemo.dtos.PersonDto;
import se.iths.springdemo.entities.Person;
import se.iths.springdemo.services.Service;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebMvcTest(PersonController.class)
//@Import(TestService.class)
public class MvcTest {

  //  @Autowired
    @MockBean
    Service service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void callingWithUrlPersonsShouldReturnAllPersonsAsJson() throws Exception {
        //Tell mockito what to return when calling methods on Service
        when(service.getAllPersons()).thenReturn(List.of(new PersonDto(1,"","")));

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/persons")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);

    }
}
