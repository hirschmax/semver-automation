package com.github.hirschm.permissions.api;

import com.github.hirschm.permissions.model.PermissionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/permissions")
public class PermissionResource {

    private final PermissionService permissionService;

    @Inject
    public PermissionResource(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(permissionService.findAll()).build();
    }

    @GET
    @Path("/{uniqueName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByUniqueName(final String uniqueName) {
        return Response.ok(permissionService.findByUniqueName(uniqueName)).build();
    }

}
