package com.github.hirschm.permissions.persistence;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PERMISSIONS")
public class PermissionEntity extends PanacheEntityBase {
    @Id
    @Column(name = "UNIQUE_NAME", nullable = false)
    public String uniqueName;
    @Column(name = "CAPTION", nullable = false)
    public String caption;
}
