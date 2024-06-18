package br.com.clinicmanager.users.controller;

import br.com.clinicmanager.users.dto.request.DoctorRequestDTO;
import br.com.clinicmanager.users.dto.response.DoctorResponseDTO;
import br.com.clinicmanager.users.service.DoctorService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Path("/api/doctors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Doctor", description = "Endpoints for managing doctors")
public class DoctorController {

    @Inject
    DoctorService doctorService;

    @GET
    @Operation(summary = "List all doctors", description = "Returns a list of all doctors")
    public List<DoctorResponseDTO> getAllDoctors() {
        return doctorService.getDoctors();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get doctor by ID", description = "Returns a doctor by its ID")
    public DoctorResponseDTO getDoctorById(@PathParam("id") UUID doctorId) {
        return doctorService.getDoctorById(doctorId);
    }

    @POST
    @Operation(summary = "Create a new doctor", description = "Creates a new doctor")
    public Response createDoctor(DoctorRequestDTO doctorRequestDTO) {
        DoctorResponseDTO createdDoctor = doctorService.createDoctor(doctorRequestDTO);
        return Response.status(Response.Status.CREATED).entity(createdDoctor).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update a doctor", description = "Updates an existing doctor")
    public Response updateDoctor(@PathParam("id") UUID doctorId, DoctorRequestDTO doctorRequestDTO) {
        DoctorResponseDTO updatedDoctor = doctorService.updateDoctor(doctorId, doctorRequestDTO);
        return Response.ok(updatedDoctor).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete a doctor", description = "Deletes a doctor by its ID")
    public Response deleteDoctor(@PathParam("id") UUID doctorId) {
        doctorService.deleteDoctor(doctorId);
        return Response.noContent().build();
    }
}
