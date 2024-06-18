package br.com.clinicmanager.users.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import br.com.clinicmanager.enums.Specialty;
import br.com.clinicmanager.enums.ProfessionalRegistryType;
import br.com.clinicmanager.clinics.entity.Clinic;
import br.com.clinicmanager.users.entity.Doctor;
import br.com.clinicmanager.users.entity.User;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DoctorRequestDTO extends UserRequestDTO {

    @NotNull(message = "Especialidade é obrigatória")
    private Specialty specialty;

    @NotNull(message = "Tipo de registro profissional é obrigatório")
    private ProfessionalRegistryType professionalRegistryType;

    @NotBlank(message = "Número do registro é obrigatório")
    private String registryNumber;

    @NotBlank(message = "Estado é obrigatório")
    private String state;

    @NotNull(message = "Validade do registro é obrigatória")
    private LocalDate registryValidity;

    public Doctor toEntity(Clinic clinic, User createdBy) {
        return new Doctor(this.getName(), this.getCpf(), this.getPhoneNumber(), this.getEmail(), this.getPassword(), this.getRole(), clinic, this.specialty, this.professionalRegistryType, this.registryNumber, this.state, this.registryValidity, createdBy);
    }
}
