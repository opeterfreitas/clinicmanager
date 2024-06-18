package br.com.clinicmanager.users.entity;

import br.com.clinicmanager.clinics.entity.Clinic;
import br.com.clinicmanager.enums.Role;
import br.com.clinicmanager.users.dto.request.ReceptionistRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import br.com.clinicmanager.enums.Shift;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Receptionist extends User {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Shift shift;

    public Receptionist(String name, String cpf, String phoneNumber, String email, String password, Role role, Clinic clinic, Shift shift, User createdBy) {
        super(name, cpf, phoneNumber, email, password, role, clinic, createdBy);
        this.shift = shift;
    }

    public Receptionist(String name, String email, String password, Shift shift) {
        super(name, email, password);
        this.shift = shift;
    }

    public Receptionist(ReceptionistRequestDTO dto, Clinic clinic, User createdBy) {
        super(dto.getName(), dto.getEmail(), dto.getPassword());
        this.shift = dto.getShift();
        this.setClinic(clinic);
        this.setCreatedBy(createdBy);
        this.setCreatedAt(LocalDateTime.now());
    }

    public Receptionist(String cpf, String name, String phoneNumber, String email, String password, User createdBy, LocalDateTime createdAt, Shift shift) {
        super(cpf, name, phoneNumber, email, password, createdBy, createdAt);
        this.shift = shift;
    }
}
