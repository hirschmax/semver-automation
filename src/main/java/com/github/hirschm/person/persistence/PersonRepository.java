package com.github.hirschm.person.persistence;

import com.github.hirschm.person.model.Person;

import java.util.List;

public interface PersonRepository {

    List<Person> findAllPersons();
    Person findPersonById(long id);
    Person findPersonByEmail(String email);
    void createPerson(Person person);
}
