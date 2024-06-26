package br.com.clinicmanager.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class ResourceNotFoundException extends WebApplicationException {

    public ResourceNotFoundException(String message) {
        super(Response.status(Response.Status.NOT_FOUND).entity(message).type("text/plain").build());
    }
}
