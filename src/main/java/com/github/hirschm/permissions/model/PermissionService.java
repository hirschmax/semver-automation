package com.github.hirschm.permissions.model;

import com.github.hirschm.permissions.persistence.PermissionEntity;

import java.util.List;

public interface PermissionService {

    List<Permission> findAll();
    Permission findByUniqueName(String uniqueName);
    default Permission toDto(final PermissionEntity entity) {
        return new Permission(entity.uniqueName, entity.caption);
    }

}
