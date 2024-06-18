package br.com.clinicmanager.users.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import br.com.clinicmanager.enums.Role;
import br.com.clinicmanager.clinics.entity.Clinic;
import br.com.clinicmanager.users.dto.request.UserRequestDTO;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "users")
public class User extends Person {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    private Clinic clinic;

    public User(String name, String cpf, String phoneNumber, String email, String password, Role role, Clinic clinic, User createdBy) {
        super(name, cpf, phoneNumber, createdBy);
        this.email = email;
        this.password = password;
        this.role = role;
        this.clinic = clinic;
    }

    public User(UserRequestDTO dto, Clinic clinic, User createdBy) {
        super(dto.getName(), dto.getCpf(), dto.getPhoneNumber(), createdBy);
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.role = dto.getRole();
        this.clinic = clinic;
    }

    public User(String name, String email, String password) {
        super.setName(name);
        this.email = email;
        this.password = password;
    }

    public User(String cpf, String name, String phoneNumber, String email, String password, User createdBy, LocalDateTime createdAt) {
        super(cpf, name, phoneNumber, createdBy, createdAt);
        this.email = email;
        this.password = password;
    }

}
