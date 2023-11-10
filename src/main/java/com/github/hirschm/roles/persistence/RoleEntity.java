package com.github.hirschm.roles.persistence;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ROLES")
public class RoleEntity extends PanacheEntity {
    @Column(name = "NAME", nullable = false)
    public String name;
}
