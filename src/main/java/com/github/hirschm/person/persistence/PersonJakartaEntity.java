package com.github.hirschm.person.persistence;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "PERSONS")
public class PersonJakartaEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    public Long id;
    @Column(name = "EMAIL", nullable = false)
    public String email;
    @Column(name = "BIRTHDATE", nullable = false)
    public LocalDate birthDate;
}
