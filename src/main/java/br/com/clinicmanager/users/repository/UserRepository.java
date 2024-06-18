package br.com.clinicmanager.users.repository;

import br.com.clinicmanager.users.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<User, UUID> {

    public Optional<User> findByIdOptional(UUID id) {
        return find("id", id).firstResultOptional();
    }

    public Optional<User> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }

    public List<User> findByRole(String role) {
        return list("role", role);
    }

    public List<User> findAllUsers() {
        return listAll();
    }
}
