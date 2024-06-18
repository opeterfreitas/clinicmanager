package br.com.clinicmanager.users.controller;

import br.com.clinicmanager.users.dto.request.UserRequestDTO;
import br.com.clinicmanager.users.dto.response.UserResponseDTO;
import br.com.clinicmanager.users.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "User", description = "Endpoints for managing users")
public class UserController {

    @Inject
    UserService userService;

    @GET
    @Operation(summary = "List all users", description = "Returns a list of all users")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getUsers();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get user by ID", description = "Returns a user by its ID")
    public UserResponseDTO getUserById(@PathParam("id") UUID userId) {
        return userService.getUserById(userId);
    }

    @POST
    @Operation(summary = "Create a new user", description = "Creates a new user")
    public Response createUser(UserRequestDTO userRequestDTO) {
        UserResponseDTO createdUser = userService.createUser(userRequestDTO);
        return Response.status(Response.Status.CREATED).entity(createdUser).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update a user", description = "Updates an existing user")
    public Response updateUser(@PathParam("id") UUID userId, UserRequestDTO userRequestDTO) {
        UserResponseDTO updatedUser = userService.updateUser(userId, userRequestDTO);
        return Response.ok(updatedUser).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete a user", description = "Deletes a user by its ID")
    public Response deleteUser(@PathParam("id") UUID userId) {
        userService.deleteUser(userId);
        return Response.noContent().build();
    }
}
