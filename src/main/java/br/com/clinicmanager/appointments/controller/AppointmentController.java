package br.com.clinicmanager.appointments.controller;

import br.com.clinicmanager.appointments.dto.request.AppointmentRequestDTO;
import br.com.clinicmanager.appointments.dto.response.AppointmentResponseDTO;
import br.com.clinicmanager.appointments.service.AppointmentService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Path("/api/appointments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Appointment", description = "Endpoints for managing appointments")
public class AppointmentController {

    @Inject
    AppointmentService appointmentService;

    @GET
    @Operation(summary = "List all appointments", description = "Returns a list of all appointments")
    public List<AppointmentResponseDTO> getAllAppointments() {
        return appointmentService.getAppointments();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get appointment by ID", description = "Returns an appointment by its ID")
    public AppointmentResponseDTO getAppointmentById(@PathParam("id") UUID appointmentId) {
        return appointmentService.getAppointmentById(appointmentId);
    }

    @POST
    @Operation(summary = "Create a new appointment", description = "Creates a new appointment")
    public Response createAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        AppointmentResponseDTO createdAppointment = appointmentService.createAppointment(appointmentRequestDTO);
        return Response.status(Response.Status.CREATED).entity(createdAppointment).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update an appointment", description = "Updates an existing appointment")
    public Response updateAppointment(@PathParam("id") UUID appointmentId, AppointmentRequestDTO appointmentRequestDTO) {
        AppointmentResponseDTO updatedAppointment = appointmentService.updateAppointment(appointmentId, appointmentRequestDTO);
        return Response.ok(updatedAppointment).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete an appointment", description = "Deletes an appointment by its ID")
    public Response deleteAppointment(@PathParam("id") UUID appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
        return Response.noContent().build();
    }
}
