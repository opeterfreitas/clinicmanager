package br.com.clinicmanager.users.controller;

import br.com.clinicmanager.users.dto.request.ReceptionistRequestDTO;
import br.com.clinicmanager.users.dto.response.ReceptionistResponseDTO;
import br.com.clinicmanager.users.service.ReceptionistService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Path("/api/receptionists")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Receptionist", description = "Endpoints for managing receptionists")
public class ReceptionistController {

    @Inject
    ReceptionistService receptionistService;

    @GET
    @Operation(summary = "List all receptionists", description = "Returns a list of all receptionists")
    public List<ReceptionistResponseDTO> getAllReceptionists() {
        return receptionistService.getReceptionists();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get receptionist by ID", description = "Returns a receptionist by its ID")
    public ReceptionistResponseDTO getReceptionistById(@PathParam("id") UUID receptionistId) {
        return receptionistService.getReceptionistById(receptionistId);
    }

    @POST
    @Operation(summary = "Create a new receptionist", description = "Creates a new receptionist")
    public Response createReceptionist(ReceptionistRequestDTO receptionistRequestDTO) {
        ReceptionistResponseDTO createdReceptionist = receptionistService.createReceptionist(receptionistRequestDTO);
        return Response.status(Response.Status.CREATED).entity(createdReceptionist).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update a receptionist", description = "Updates an existing receptionist")
    public Response updateReceptionist(@PathParam("id") UUID receptionistId, ReceptionistRequestDTO receptionistRequestDTO) {
        ReceptionistResponseDTO updatedReceptionist = receptionistService.updateReceptionist(receptionistId, receptionistRequestDTO);
        return Response.ok(updatedReceptionist).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete a receptionist", description = "Deletes a receptionist by its ID")
    public Response deleteReceptionist(@PathParam("id") UUID receptionistId) {
        receptionistService.deleteReceptionist(receptionistId);
        return Response.noContent().build();
    }
}
