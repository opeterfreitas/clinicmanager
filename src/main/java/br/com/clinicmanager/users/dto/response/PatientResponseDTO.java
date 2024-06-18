package br.com.clinicmanager.users.dto.response;

import lombok.Data;
import br.com.clinicmanager.users.entity.Patient;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PatientResponseDTO extends UserResponseDTO {

    private String medicalHistory;
    private String allergies;
    private String currentMedications;

    public PatientResponseDTO(Patient patient) {
        super(patient);
        this.medicalHistory = patient.getMedicalHistory();
        this.allergies = patient.getAllergies();
        this.currentMedications = patient.getCurrentMedications();
    }
}
