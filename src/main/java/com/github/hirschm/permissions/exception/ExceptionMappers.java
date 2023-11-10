package com.github.hirschm.permissions.exception;

import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class ExceptionMappers {

    @ServerExceptionMapper
    public RestResponse<String> mapPermissionNotFoundException(PermissionNotFoundException permissionNotFoundException) {
        return RestResponse.status(Response.Status.NOT_FOUND, permissionNotFoundException.message);
    }

}
