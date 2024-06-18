package br.com.clinicmanager.appointments.dto.response;

import br.com.clinicmanager.appointments.entity.Appointment;
import br.com.clinicmanager.enums.AppointmentStatus;
import br.com.clinicmanager.users.entity.Doctor;
import br.com.clinicmanager.users.entity.Patient;
import br.com.clinicmanager.users.entity.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AppointmentResponseDTO {

    private UUID id;
    private UUID doctorId;
    private UUID patientId;
    private LocalDateTime appointmentDate;
    private AppointmentStatus status;
    private UUID createdBy;
    private LocalDateTime createdAt;
    private UUID updatedBy;
    private LocalDateTime updatedAt;

    public AppointmentResponseDTO(Appointment appointment) {
        this.id = appointment.getId();
        this.doctorId = getIdOrNull(appointment.getDoctor());
        this.patientId = getIdOrNull(appointment.getPatient());
        this.appointmentDate = appointment.getAppointmentDate();
        this.status = appointment.getStatus();
        this.createdBy = getIdOrNull(appointment.getCreatedBy());
        this.createdAt = appointment.getCreatedAt();
        this.updatedBy = getIdOrNull(appointment.getUpdatedBy());
        this.updatedAt = appointment.getUpdatedAt();
    }

    private UUID getIdOrNull(User user) {
        return user != null ? user.getId() : null;
    }

    private UUID getIdOrNull(Doctor doctor) {
        return doctor != null ? doctor.getId() : null;
    }

    private UUID getIdOrNull(Patient patient) {
        return patient != null ? patient.getId() : null;
    }
}
