package br.com.clinicmanager.users.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import br.com.clinicmanager.enums.Role;
import br.com.clinicmanager.clinics.entity.Clinic;
import br.com.clinicmanager.users.entity.User;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    @NotBlank(message = "Número de telefone é obrigatório")
    private String phoneNumber;

    @Email(message = "Email não é válido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    private String password;

    @NotNull(message = "Role é obrigatório")
    private Role role;

    @NotNull(message = "ID da clínica é obrigatório")
    private UUID clinicId;

    public User toEntity(Clinic clinic) {
        return new User(this.name, this.cpf, this.phoneNumber, this.email, this.password, this.role, clinic, null);
    }
}
