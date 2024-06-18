package br.com.clinicmanager.users.entity;

import br.com.clinicmanager.clinics.entity.Clinic;
import br.com.clinicmanager.enums.Role;
import br.com.clinicmanager.users.dto.request.PatientRequestDTO;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Patient extends User {

    private String medicalHistory;
    private String allergies;
    private String currentMedications;

    public Patient(String name, String cpf, String phoneNumber, String email, String password, Role role, Clinic clinic, String medicalHistory, String allergies, String currentMedications, User createdBy) {
        super(name, cpf, phoneNumber, email, password, role, clinic, createdBy);
        this.medicalHistory = medicalHistory;
        this.allergies = allergies;
        this.currentMedications = currentMedications;
    }

    public Patient(String name, String email, String password, String medicalHistory, String allergies, String currentMedications) {
        super(name, email, password);
        this.medicalHistory = medicalHistory;
        this.allergies = allergies;
        this.currentMedications = currentMedications;
    }

    public Patient(PatientRequestDTO dto, Clinic clinic, User createdBy) {
        super(dto.getName(), dto.getEmail(), dto.getPassword());
        this.medicalHistory = dto.getMedicalHistory();
        this.allergies = dto.getAllergies();
        this.currentMedications = dto.getCurrentMedications();
        this.setClinic(clinic);
        this.setCreatedBy(createdBy);
        this.setCreatedAt(LocalDateTime.now());
    }
}
