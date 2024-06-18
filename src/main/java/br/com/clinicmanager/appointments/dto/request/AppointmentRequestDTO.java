package br.com.clinicmanager.appointments.dto.request;

import br.com.clinicmanager.appointments.entity.Appointment;
import br.com.clinicmanager.enums.AppointmentStatus;
import br.com.clinicmanager.users.entity.Doctor;
import br.com.clinicmanager.users.entity.Patient;
import br.com.clinicmanager.users.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AppointmentRequestDTO {

    @NotNull(message = "Doctor ID is required")
    private UUID doctorId;

    @NotNull(message = "Patient ID is required")
    private UUID patientId;

    @NotNull(message = "Appointment date is required")
    private LocalDateTime appointmentDate;

    @NotNull(message = "Status is required")
    private AppointmentStatus status;

    public Appointment toEntity(Doctor doctor, Patient patient, User createdBy) {
        return new Appointment(this, doctor, patient, createdBy);
    }
}
