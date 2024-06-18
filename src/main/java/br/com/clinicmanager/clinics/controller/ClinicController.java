package br.com.clinicmanager.clinics.controller;

import br.com.clinicmanager.clinics.dto.request.ClinicRequestDTO;
import br.com.clinicmanager.clinics.dto.response.ClinicResponseDTO;
import br.com.clinicmanager.clinics.service.ClinicService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Path("/api/clinics")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Clinic", description = "Endpoints for managing clinics")
public class ClinicController {

    @Inject
    ClinicService clinicService;

    @GET
    @Path("/{id}")
    @Operation(summary = "Get clinic by ID", description = "Returns a clinic by its ID")
    public ClinicResponseDTO getClinicById(@PathParam("id") UUID clinicId) {
        return clinicService.getClinicById(clinicId);
    }

    @POST
    @Operation(summary = "Create a new clinic", description = "Creates a new clinic")
    public Response createClinic(ClinicRequestDTO clinicRequestDTO) {
        ClinicResponseDTO createdClinic = clinicService.createClinic(clinicRequestDTO);
        return Response.status(Response.Status.CREATED).entity(createdClinic).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update a clinic", description = "Updates an existing clinic")
    public Response updateClinic(@PathParam("id") UUID clinicId, ClinicRequestDTO clinicRequestDTO) {
        ClinicResponseDTO updatedClinic = clinicService.updateClinic(clinicId, clinicRequestDTO);
        return Response.ok(updatedClinic).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete a clinic", description = "Deletes a clinic by its ID")
    public Response deleteClinic(@PathParam("id") UUID clinicId) {
        clinicService.deleteClinic(clinicId);
        return Response.noContent().build();
    }
}
