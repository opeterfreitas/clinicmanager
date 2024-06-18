package br.com.clinicmanager.config;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import io.quarkus.security.identity.SecurityIdentity;

@RequestScoped
public class SecurityIdentityWrapper {

    @Inject
    SecurityIdentity securityIdentity;

    public String getUsername() {
        return securityIdentity.getPrincipal().getName();
    }

    public boolean hasRole(String role) {
        return securityIdentity.getRoles().contains(role);
    }

    public SecurityIdentity getSecurityIdentity() {
        return securityIdentity;
    }
}
