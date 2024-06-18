package br.com.clinicmanager.clinics.dto.request;

import br.com.clinicmanager.clinics.entity.Clinic;
import br.com.clinicmanager.clinics.enums.ClinicStatus;
import br.com.clinicmanager.plans.entity.Plan;
import br.com.clinicmanager.users.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ClinicRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @NotBlank(message = "Endereço é obrigatório")
    private String address;

    @NotBlank(message = "Número de telefone é obrigatório")
    private String phoneNumber;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    private String email;

    @NotNull(message = "ID do plano é obrigatório")
    private UUID planId;

    @NotBlank(message = "CNPJ é obrigatório")
    @Pattern(regexp = "\\d{14}", message = "CNPJ deve ter 14 dígitos")
    private String cnpj;

    @NotNull(message = "ID do usuário responsável é obrigatório")
    private UUID responsibleUserId;

    @NotNull(message = "Status é obrigatório")
    private ClinicStatus status;

    public Clinic toEntity(Plan plan, User responsible, User createdBy) {
        Clinic clinic = new Clinic();
        clinic.setName(this.name);
        clinic.setAddress(this.address);
        clinic.setPhoneNumber(this.phoneNumber);
        clinic.setEmail(this.email);
        clinic.setPlan(plan);
        clinic.setCnpj(this.cnpj);
        clinic.setStatus(this.status);
        clinic.setCreatedBy(createdBy);
        clinic.setCreatedAt(LocalDateTime.now());
        clinic.setUpdatedBy(responsible);
        clinic.setUpdatedAt(LocalDateTime.now());
        return clinic;
    }
}
