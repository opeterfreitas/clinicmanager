package br.com.clinicmanager.users.repository;

import br.com.clinicmanager.users.entity.Patient;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class PatientRepository implements PanacheRepositoryBase<Patient, UUID> {

    public Optional<Patient> findByIdOptional(UUID id) {
        return find("id", id).firstResultOptional();
    }

    public Optional<Patient> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }

    public List<Patient> findAllPatients() {
        return listAll();
    }
}
