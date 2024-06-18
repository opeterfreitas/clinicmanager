package br.com.clinicmanager.clinics.repository;

import br.com.clinicmanager.clinics.entity.Clinic;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ClinicRepository implements PanacheRepositoryBase<Clinic, UUID> {

    public Optional<Clinic> findByIdOptional(UUID id) {
        return find("id", id).firstResultOptional();
    }

    public List<Clinic> findAllByResponsible(UUID responsibleId) {
        return list("responsible.id", responsibleId);
    }

    public List<Clinic> findByName(String name) {
        return list("name", name);
    }

    public List<Clinic> findAllClinics() {
        return listAll();
    }
}
