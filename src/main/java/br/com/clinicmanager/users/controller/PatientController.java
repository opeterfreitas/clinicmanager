package br.com.clinicmanager.users.controller;

import br.com.clinicmanager.users.dto.request.PatientRequestDTO;
import br.com.clinicmanager.users.dto.response.PatientResponseDTO;
import br.com.clinicmanager.users.service.PatientService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Path("/api/patients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Patient", description = "Endpoints for managing patients")
public class PatientController {

    @Inject
    PatientService patientService;

    @GET
    @Operation(summary = "List all patients", description = "Returns a list of all patients")
    public List<PatientResponseDTO> getAllPatients() {
        return patientService.getPatients();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get patient by ID", description = "Returns a patient by its ID")
    public PatientResponseDTO getPatientById(@PathParam("id") UUID patientId) {
        return patientService.getPatientById(patientId);
    }

    @POST
    @Operation(summary = "Create a new patient", description = "Creates a new patient")
    public Response createPatient(PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO createdPatient = patientService.createPatient(patientRequestDTO);
        return Response.status(Response.Status.CREATED).entity(createdPatient).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update a patient", description = "Updates an existing patient")
    public Response updatePatient(@PathParam("id") UUID patientId, PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO updatedPatient = patientService.updatePatient(patientId, patientRequestDTO);
        return Response.ok(updatedPatient).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete a patient", description = "Deletes a patient by its ID")
    public Response deletePatient(@PathParam("id") UUID patientId) {
        patientService.deletePatient(patientId);
        return Response.noContent().build();
    }
}
