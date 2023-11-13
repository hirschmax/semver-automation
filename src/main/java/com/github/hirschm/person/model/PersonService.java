package com.github.hirschm.person.model;

import com.github.hirschm.person.persistence.PersonJakartaRepository;
import com.github.hirschm.person.persistence.PersonPanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PersonService {

    private final PersonPanacheRepository panacheRepository;
    private final PersonJakartaRepository jakartaRepository;

    public PersonService(PersonPanacheRepository panacheRepository, PersonJakartaRepository jakartaRepository) {
        this.panacheRepository = panacheRepository;
        this.jakartaRepository = jakartaRepository;
    }

    public void createPersonPanache(Person person) {
        panacheRepository.createPerson(person);
    }

    public List<Person> findAllPanache() {
        return panacheRepository.findAllPersons();
    }

    public Person findByIdPanache(long id) {
        return panacheRepository.findPersonById(id);
    }

    public Person findByEmailPanache(String email) {
        return panacheRepository.findPersonByEmail(email);
    }

    public List<Person> findAllJakarta() {
        return jakartaRepository.findAllPersons();
    }

    public Person findByIdJakarta(long id) {
        return jakartaRepository.findPersonById(id);
    }

    public Person findByEmailJakarta(String email) {
        return jakartaRepository.findPersonByEmail(email);
    }

    public void createPersonJakarta(Person person) {
        jakartaRepository.createPerson(person);
    }
}
