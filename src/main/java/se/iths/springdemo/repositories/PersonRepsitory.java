package se.iths.springdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.springdemo.entities.Person;

import java.util.List;

@Repository
public interface PersonRepsitory extends JpaRepository<Person, Long> {
    List<Person> findAllByName(String name);
}
