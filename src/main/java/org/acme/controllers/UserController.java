package org.acme.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.models.UserEntity;
import org.acme.services.UserService;

import java.util.Collection;

// @Path: Sets the base URL path for this resource. All endpoints here will start with "/user".
@Path("/user")
// @Produces: Declares that responses from this class will be serialized as JSON by default.
@Produces(MediaType.APPLICATION_JSON)
// @Consumes: Declares that this class expects request bodies to be in JSON format.
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    // @Inject: Tells CDI to automatically provide an instance of UserService here.
    @Inject
    UserService userService;

    // @GET: Maps this method to HTTP GET requests at "/user". Returns all users.
    @GET
    public Collection<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    // @GET: Maps this method to HTTP GET requests.
    // @Path("{id}"): Extends the base path so thThis project uses Quarkus, the Supersonic Subatomic Java Framework.

    @GET
    @Path("{id}")
    public Response getUserById(
            // @PathParam("id"): Extracts the {id} segment from the URL and binds it to this parameter.
            @PathParam("id") Long id) {
        return userService.getUserById(id)
                .map(user -> Response.ok(user).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    // @POST: Maps this method to HTTP POST requests at "/user". Used to create a new user.
    @POST
    public Response createUser(UserEntity user) {
        UserEntity created = userService.createUser(user);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    // @PUT: Maps this method to HTTP PUT requests. Used to fully replace an existing user.
    // @Path("{id}"): Extends the base path so this endpoint listens at "/user/{id}".
    @PUT
    @Path("{id}")
    public Response updateUser(
            // @PathParam("id"): Extracts the {id} segment from the URL and binds it to this parameter.
            @PathParam("id") Long id,
            UserEntity user) {
        return userService.updateUser(id, user)
                .map(updated -> Response.ok(updated).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    // @DELETE: Maps this method to HTTP DELETE requests. Used to remove a user.
    // @Path("{id}"): Extends the base path so this endpoint listens at "/user/{id}".
    @DELETE
    @Path("{id}")
    public Response deleteUser(
            // @PathParam("id"): Extracts the {id} segment from the URL and binds it to this parameter.
            @PathParam("id") Long id) {
        boolean deleted = userService.deleteUser(id);
        return deleted
                ? Response.noContent().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }
}