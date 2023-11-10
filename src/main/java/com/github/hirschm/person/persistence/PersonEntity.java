package com.github.hirschm.person.persistence;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "PERSONS")
public class PersonEntity extends PanacheEntity {
    @Column(name = "EMAIL", nullable = false)
    public String email;
    @Column(name = "BIRTHDATE", nullable = false)
    public LocalDate birthDate;
}
