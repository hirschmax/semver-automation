package com.github.hirschm.permissions.model;

import com.github.hirschm.permissions.exception.PermissionNotFoundException;
import com.github.hirschm.permissions.persistence.PermissionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;

@ApplicationScoped
public class PermissionServiceImpl implements PermissionService {

    @ConfigProperty(name = "messages.exception.permissions.not-found")
    String notFoundErrorMessage;

    private final PermissionRepository permissionRepository;

    @Inject
    public PermissionServiceImpl(final PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<Permission> findAll() {
        return permissionRepository
                .findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public Permission findByUniqueName(final String uniqueName) {
        return permissionRepository
                .findByUniqueName(uniqueName)
                .map(this::toDto)
                .orElseThrow(() -> newPermissionNotFoundException(uniqueName));
    }

    private PermissionNotFoundException newPermissionNotFoundException(final String uniqueName) {
        return new PermissionNotFoundException(String.format(notFoundErrorMessage, uniqueName));
    }
}
