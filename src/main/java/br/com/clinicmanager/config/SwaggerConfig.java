package br.com.clinicmanager.config;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@OpenAPIDefinition(info = @Info(title = "Clinic Manager API", version = "1.0", description = "API for clinic management and telemedicine"))
@ApplicationPath("/api")
public class SwaggerConfig extends Application {
}
