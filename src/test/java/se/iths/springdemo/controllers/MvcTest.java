package se.iths.springdemo.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import se.iths.springdemo.configurations.TestConfiguration;
import se.iths.springdemo.dtos.PersonDto;
import se.iths.springdemo.services.Service;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.client.match.JsonPathRequestMatchers.*;

@WebMvcTest(PersonController.class)
@Import(TestConfiguration.class)
//@Import(TestService.class)
public class MvcTest {

    @MockBean
    Service service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper jsonMapper;

    @Test
    void callingWithUrlPersonsShouldReturnAllPersonsAsJson() throws Exception {
        //Tell mockito what to return when callingfmethods on Service
        when(service.getAllPersons()).thenReturn(List.of(new PersonDto(1,"","")));

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/persons")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void callingPOSTWithNewPersonShouldSavePersonToServiceAndReturnNewPersonWithId() throws Exception {
        //Tell mockito what to return when callingfmethods on Service
        var personDto = new PersonDto(0,"Kalle","Kalle");

        when(service.createPerson(eq(personDto))).thenReturn(new PersonDto(1,"Kalle","Kalle"));

        var result = mockMvc.perform(MockMvcRequestBuilders.post("/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsBytes(personDto))
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(201);
    }
}
