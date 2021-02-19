package se.iths.springdemo.mappers;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import se.iths.springdemo.dtos.PersonDto;
import se.iths.springdemo.entities.Person;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PersonMapper {
    public PersonMapper() {
    }

    public PersonDto mapp(Person person) {
        return new PersonDto(person.getId(), person.getName(), person.getEmail());
    }

    public Person mapp(PersonDto personDto) {
        return new Person(personDto.getId(), personDto.getName(), personDto.getEmail());
    }

    public Optional<PersonDto> mapp(Optional<Person> optionalPerson) {
        if (optionalPerson.isEmpty())
            return Optional.empty();
        return Optional.of(mapp(optionalPerson.get()));
    }

    public List<PersonDto> mapp(List<Person> all) {

        return all
                .stream()
                .map(this::mapp)
                .collect(Collectors.toList());

//        List<PersonDto> personDtoList = new ArrayList<>();
//        for (var person: all ) {
//            personDtoList.add(mapp(person));
//        }
//        return personDtoList;
    }
}