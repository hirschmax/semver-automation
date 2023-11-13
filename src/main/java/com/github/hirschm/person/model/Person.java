package com.github.hirschm.person.model;

import java.time.LocalDate;

public class Person {
    private final Long id;
    private final String email;
    private final LocalDate birthDate;

    public Person(Long id, String email, LocalDate birthDate) {
        this.id = id;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
