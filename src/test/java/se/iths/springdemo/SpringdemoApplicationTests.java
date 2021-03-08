package se.iths.springdemo;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;
import se.iths.springdemo.dtos.PersonDto;
import se.iths.springdemo.entities.Person;

import java.net.URI;
import java.net.http.HttpClient;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringdemoApplicationTests {

	@LocalServerPort
	int port;

	@Autowired
	TestRestTemplate testClient;


	void setUp(){

	}

	@Test
	void contextLoads() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/xml");
		//testClient.exchange("localhost:8080/person", HttpMethod.GET, new HttpEntity<>(headers), PersonDto[].class);

		var result = testClient.getForEntity("http://localhost:"+port+"/persons", PersonDto[].class);
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result.getBody().length).isGreaterThan(0);
	}

	@Test
	void postSomethingToService() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		//testClient.exchange("localhost:8080/person", HttpMethod.GET, new HttpEntity<>(headers), PersonDto[].class);
		PersonDto personDto = new PersonDto(0,"test","test");
		var result = testClient.postForEntity("http://localhost:"+port+"/persons",personDto, PersonDto.class);
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		//Eventually verify with a get request for person with id
	}

	@Test
	void searchWithParameters() {
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Accept", "application/json");
//		//testClient.exchange("localhost:8080/person", HttpMethod.GET, new HttpEntity<>(headers), PersonDto[].class);
//		PersonDto personDto = new PersonDto(0,"test","test");
		URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:"+port+"/persons").path("/search")
				.queryParam("genre", "Comedy").build().toUri();
		var result = testClient.getForEntity(uri, PersonDto.class);

		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		//Eventually verify with a get request for person with id
	}






}
