package br.com.clinicmanager.users.dto.request;

import lombok.Data;
import br.com.clinicmanager.clinics.entity.Clinic;
import br.com.clinicmanager.users.entity.Patient;
import br.com.clinicmanager.users.entity.User;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PatientRequestDTO extends UserRequestDTO {

    private String medicalHistory;
    private String allergies;
    private String currentMedications;

    public Patient toEntity(Clinic clinic, User createdBy) {
        return new Patient(this.getName(), this.getCpf(), this.getPhoneNumber(), this.getEmail(), this.getPassword(), this.getRole(), clinic, this.medicalHistory, this.allergies, this.currentMedications, createdBy);
    }
}
