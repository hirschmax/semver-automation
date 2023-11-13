package com.github.hirschm.person.exception;

public class PersonNotFoundException extends RuntimeException {
    public final Long personId;
    public final String email;

    public PersonNotFoundException(Long id) {
        super(String.format("Person with id %s not found!", id));
        this.personId = id;
        this.email = null;
    }
    public PersonNotFoundException(String email) {
        super(String.format("Person with email %s not found!", email));
        this.personId = null;
        this.email = email;
    }
}
