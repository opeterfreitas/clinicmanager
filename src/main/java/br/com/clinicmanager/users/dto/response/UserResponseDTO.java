package br.com.clinicmanager.users.dto.response;

import lombok.Data;
import br.com.clinicmanager.users.entity.User;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserResponseDTO {

    private String id;
    private String name;
    private String cpf;
    private String phoneNumber;
    private String email;
    private String role;
    private String clinic;

    public UserResponseDTO(User user) {
        this.id = user.getId().toString();
        this.name = user.getName();
        this.cpf = user.getCpf();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
        this.role = user.getRole().name();
        this.clinic = user.getClinic() != null ? user.getClinic().getName() : null;
    }
}
