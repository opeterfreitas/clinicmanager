package br.com.clinicmanager.users.repository;

import br.com.clinicmanager.users.entity.Doctor;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class DoctorRepository implements PanacheRepositoryBase<Doctor, UUID> {

    public Optional<Doctor> findByIdOptional(UUID id) {
        return find("id", id).firstResultOptional();
    }

    public Optional<Doctor> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }

    public List<Doctor> findBySpecialty(String specialty) {
        return list("specialty", specialty);
    }

    public List<Doctor> findAllDoctors() {
        return listAll();
    }
}
