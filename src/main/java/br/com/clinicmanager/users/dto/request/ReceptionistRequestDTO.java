package br.com.clinicmanager.users.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import br.com.clinicmanager.enums.Shift;
import br.com.clinicmanager.clinics.entity.Clinic;
import br.com.clinicmanager.users.entity.Receptionist;
import br.com.clinicmanager.users.entity.User;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReceptionistRequestDTO extends UserRequestDTO {

    @NotNull(message = "Turno é obrigatório")
    private Shift shift;

    public Receptionist toEntity(Clinic clinic, User createdBy) {
        return new Receptionist(this.getName(), this.getCpf(), this.getPhoneNumber(), this.getEmail(), this.getPassword(), this.getRole(), clinic, this.shift, createdBy);
    }
}
