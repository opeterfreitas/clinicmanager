package br.com.clinicmanager.users.dto.response;

import lombok.Data;
import br.com.clinicmanager.users.entity.Receptionist;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReceptionistResponseDTO extends UserResponseDTO {

    private String shift;

    public ReceptionistResponseDTO(Receptionist receptionist) {
        super(receptionist);
        this.shift = receptionist.getShift().name();
    }
}
