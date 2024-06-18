package br.com.clinicmanager.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.security.Principal;

@Provider
@ApplicationScoped
public class SecurityConfig implements ContainerRequestFilter {

    private final JsonWebToken jwt;

    public SecurityConfig(JsonWebToken jwt) {
        this.jwt = jwt;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) {
        SecurityContext securityContext = requestContext.getSecurityContext();
        Principal userPrincipal = securityContext.getUserPrincipal();

        if (userPrincipal != null) {
            String username = userPrincipal.getName();
            if (jwt != null && username.equals(jwt.getName())) {
                // Authenticated with JWT
                requestContext.setSecurityContext(new SecurityContext() {
                    @Override
                    public Principal getUserPrincipal() {
                        return jwt;
                    }

                    @Override
                    public boolean isUserInRole(String role) {
                        return jwt.getGroups().contains(role);
                    }

                    @Override
                    public boolean isSecure() {
                        return requestContext.getSecurityContext().isSecure();
                    }

                    @Override
                    public String getAuthenticationScheme() {
                        return "Bearer";
                    }
                });
            } else {
                // Authenticated in another way
            }
        } else {
            // Not authenticated
        }
    }
}
