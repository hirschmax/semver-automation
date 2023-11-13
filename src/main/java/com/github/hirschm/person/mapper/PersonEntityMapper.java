package com.github.hirschm.person.mapper;

import com.github.hirschm.person.model.Person;
import com.github.hirschm.person.persistence.PersonEntity;
import com.github.hirschm.person.persistence.PersonJakartaEntity;
import com.github.hirschm.person.persistence.PersonJakartaRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonEntityMapper {
    public Person entityToDomain(PersonEntity personEntity) {
        return new Person(personEntity.id, personEntity.email, personEntity.birthDate);
    }
    public Person entityToDomain(PersonJakartaEntity personEntity) {
        return new Person(personEntity.id, personEntity.email, personEntity.birthDate);
    }

    public PersonJakartaEntity domainToJakartaEntity(Person person) {
        PersonJakartaEntity personEntity = new PersonJakartaEntity();
        personEntity.id = person.getId();
        personEntity.email = person.getEmail();
        personEntity.birthDate = person.getBirthDate();
        return personEntity;
    }
    public PersonEntity domainToEntity(Person person) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.id = person.getId();
        personEntity.email = person.getEmail();
        personEntity.birthDate = person.getBirthDate();
        return personEntity;
    }
}
