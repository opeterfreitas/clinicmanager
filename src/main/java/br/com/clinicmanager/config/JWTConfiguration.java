package br.com.clinicmanager.config;

import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JWTConfiguration {

    @ConfigProperty(name = "mp.jwt.verify.issuer")
    String issuer;

    public String generateToken(String username, Set<String> roles) {
        return Jwt.issuer(issuer)
                .upn(username)
                .groups(roles)
                .sign();
    }
}
