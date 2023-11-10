package com.github.hirschm.permissions.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class PermissionRepository implements PanacheRepositoryBase<PermissionEntity, String> {

    public Optional<PermissionEntity> findByUniqueName(final String uniqueName) {
        return find("uniqueName = :uname", Parameters.with("uname", uniqueName))
                .firstResultOptional();
    }

}
