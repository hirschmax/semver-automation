package com.github.hirschm.person;

import com.github.hirschm.person.model.Person;
import com.github.hirschm.person.model.PersonService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/person")
public class PersonResource {
    private final PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @GET
    @Path("/all/panache")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllPanache() {
        return Response.ok(personService.findAllPanache()).build();
    }
    @GET
    @Path("/email/{email}/panache")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByEmailPanache(String email) {
        return Response.ok(personService.findByEmailPanache(email)).build();
    }

    @GET
    @Path("/email/{id}/panache")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdPanache(long id) {
        return Response.ok(personService.findByIdPanache(id)).build();
    }

    @POST
    @Path("/create/panache")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPanache(Person person) {
        personService.createPersonPanache(person);
        return Response.ok().build();
    }

    @GET
    @Path("/all/jakarta")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllJakarta() {
        return Response.ok(personService.findAllPanache()).build();
    }

    @GET
    @Path("/email/{email}/jakarta")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByEmailJakarta(String email) {
        return Response.ok(personService.findByEmailJakarta(email)).build();
    }

    @GET
    @Path("/email/{id}/jakarta")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdJakarta(long id) {
        return Response.ok(personService.findByIdJakarta(id)).build();
    }

    @POST
    @Path("/create/jakarta")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createJakarta(Person person) {
        personService.createPersonJakarta(person);
        return Response.ok().build();
    }


}
