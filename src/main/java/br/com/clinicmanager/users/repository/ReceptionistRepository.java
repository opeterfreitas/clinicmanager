package br.com.clinicmanager.users.repository;

import br.com.clinicmanager.users.entity.Receptionist;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ReceptionistRepository implements PanacheRepositoryBase<Receptionist, UUID> {

    public Optional<Receptionist> findByIdOptional(UUID id) {
        return find("id", id).firstResultOptional();
    }

    public Optional<Receptionist> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }

    public List<Receptionist> findByShift(String shift) {
        return list("shift", shift);
    }

    public List<Receptionist> findAllReceptionists() {
        return listAll();
    }
}
