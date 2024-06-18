package br.com.clinicmanager.users.dto.response;

import lombok.Data;
import br.com.clinicmanager.users.entity.Doctor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DoctorResponseDTO extends UserResponseDTO {

    private String specialty;
    private String professionalRegistryType;
    private String registryNumber;
    private String state;
    private String registryValidity;

    public DoctorResponseDTO(Doctor doctor) {
        super(doctor);
        this.specialty = doctor.getSpecialty().name();
        this.professionalRegistryType = doctor.getProfessionalRegistryType().name();
        this.registryNumber = doctor.getRegistryNumber();
        this.state = doctor.getState();
        this.registryValidity = doctor.getRegistryValidity().toString();
    }
}
