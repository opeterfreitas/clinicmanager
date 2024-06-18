package br.com.clinicmanager.notifications.controller;

import br.com.clinicmanager.notifications.dto.request.NotificationRequestDTO;
import br.com.clinicmanager.notifications.dto.response.NotificationResponseDTO;
import br.com.clinicmanager.notifications.service.NotificationService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Path("/api/notifications")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Notification", description = "Endpoints for managing notifications")
public class NotificationController {

    @Inject
    NotificationService notificationService;

    @GET
    @Operation(summary = "List all notifications", description = "Returns a list of all notifications")
    public List<NotificationResponseDTO> getAllNotifications() {
        return notificationService.getNotifications();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get notification by ID", description = "Returns a notification by its ID")
    public NotificationResponseDTO getNotificationById(@PathParam("id") UUID notificationId) {
        return notificationService.getNotificationById(notificationId);
    }

    @POST
    @Operation(summary = "Create a new notification", description = "Creates a new notification")
    public Response createNotification(NotificationRequestDTO notificationRequestDTO) {
        NotificationResponseDTO createdNotification = notificationService.createNotification(notificationRequestDTO);
        return Response.status(Response.Status.CREATED).entity(createdNotification).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update a notification", description = "Updates an existing notification")
    public Response updateNotification(@PathParam("id") UUID notificationId, NotificationRequestDTO notificationRequestDTO) {
        NotificationResponseDTO updatedNotification = notificationService.updateNotification(notificationId, notificationRequestDTO);
        return Response.ok(updatedNotification).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete a notification", description = "Deletes a notification by its ID")
    public Response deleteNotification(@PathParam("id") UUID notificationId) {
        notificationService.deleteNotification(notificationId);
        return Response.noContent().build();
    }
}
