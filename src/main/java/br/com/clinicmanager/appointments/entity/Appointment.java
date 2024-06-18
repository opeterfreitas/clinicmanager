package br.com.clinicmanager.appointments.entity;

import br.com.clinicmanager.enums.AppointmentStatus;
import br.com.clinicmanager.users.entity.Doctor;
import br.com.clinicmanager.users.entity.Patient;
import br.com.clinicmanager.users.entity.User;
import br.com.clinicmanager.appointments.dto.request.AppointmentRequestDTO;
import br.com.clinicmanager.appointments.dto.response.AppointmentResponseDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(nullable = false)
    private LocalDateTime appointmentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    @Column
    private LocalDateTime updatedAt;

    public Appointment(AppointmentRequestDTO dto, Doctor doctor, Patient patient, User createdBy) {
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentDate = dto.getAppointmentDate();
        this.status = dto.getStatus();
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
    }

    public AppointmentResponseDTO toResponseDTO() {
        return new AppointmentResponseDTO(this);
    }
}
