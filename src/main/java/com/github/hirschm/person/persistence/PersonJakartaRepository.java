package com.github.hirschm.person.persistence;

import com.github.hirschm.person.exception.PersonNotFoundException;
import com.github.hirschm.person.mapper.PersonEntityMapper;
import com.github.hirschm.person.model.Person;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

import java.util.List;


@ApplicationScoped
public class PersonJakartaRepository implements PersonRepository {

    private final EntityManager entityManager;
    private final PersonEntityMapper personEntityMapper;

    public PersonJakartaRepository(EntityManager entityManager, PersonEntityMapper personEntityMapper) {
        this.entityManager = entityManager;
        this.personEntityMapper = personEntityMapper;
    }

    @Override
    public List<Person> findAllPersons() {
        return entityManager.createQuery("SELECT p FROM PersonEntity p", PersonJakartaEntity.class)
                .getResultStream()
                .map(personEntityMapper::entityToDomain)
                .toList();
    }

    @Override
    public Person findPersonById(long id) {
        return entityManager.createQuery("SELECT p FROM PersonEntity p WHERE p.id = :id", PersonJakartaEntity.class)
                .setParameter("id", id)
                .getResultStream()
                .map(personEntityMapper::entityToDomain)
                .findFirst()
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @Override
    public Person findPersonByEmail(String email) {
        return entityManager.createQuery("SELECT p FROM PersonEntity p WHERE p.email = :email", PersonJakartaEntity.class)
                .setParameter("email", email)
                .getResultStream()
                .map(personEntityMapper::entityToDomain)
                .findFirst()
                .orElseThrow(() -> new PersonNotFoundException(email));
    }

    @Override
    public void createPerson(Person person) {
        entityManager.merge(personEntityMapper.domainToEntity(person));
    }
}
