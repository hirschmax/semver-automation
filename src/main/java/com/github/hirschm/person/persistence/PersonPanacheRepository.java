package com.github.hirschm.person.persistence;

import com.github.hirschm.person.exception.PersonNotFoundException;
import com.github.hirschm.person.mapper.PersonEntityMapper;
import com.github.hirschm.person.model.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PersonPanacheRepository implements PanacheRepository<PersonEntity>, PersonRepository {

    private final PersonEntityMapper personEntityMapper;

    public PersonPanacheRepository(PersonEntityMapper personEntityMapper) {
        this.personEntityMapper = personEntityMapper;
    }

    @Override
    public List<Person> findAllPersons() {
        return findAll().stream().map(personEntityMapper::entityToDomain).toList();
    }

    @Override
    public Person findPersonById(long id) {
        PersonEntity personEntity = findById(id);
        return Optional.ofNullable(personEntity)
                .map(personEntityMapper::entityToDomain)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @Override
    public Person findPersonByEmail(String email) {
        Optional<PersonEntity> mail = find("email = :mail", Parameters.with("mail", email)).firstResultOptional();
        return mail.map(personEntityMapper::entityToDomain)
                .orElseThrow(() -> new PersonNotFoundException(email));
    }

    @Override
    public void createPerson(Person person) {
        persist(personEntityMapper.domainToEntity(person));
    }

}
