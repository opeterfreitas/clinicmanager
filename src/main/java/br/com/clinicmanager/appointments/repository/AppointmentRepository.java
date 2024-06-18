package br.com.clinicmanager.appointments.repository;

import br.com.clinicmanager.appointments.entity.Appointment;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class AppointmentRepository implements PanacheRepositoryBase<Appointment, UUID> {

    public Optional<Appointment> findByIdOptional(UUID id) {
        return find("id", id).firstResultOptional();
    }

    public List<Appointment> findByDoctorId(UUID doctorId) {
        return list("doctor.id", doctorId);
    }

    public List<Appointment> findByPatientId(UUID patientId) {
        return list("patient.id", patientId);
    }

    public List<Appointment> findByStatus(String status) {
        return list("status", status);
    }

    public List<Appointment> findAllAppointments() {
        return listAll();
    }
}
