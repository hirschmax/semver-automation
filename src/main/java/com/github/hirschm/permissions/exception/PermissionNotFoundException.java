package com.github.hirschm.permissions.exception;

public class PermissionNotFoundException extends RuntimeException {
    public final String message;
    public PermissionNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
