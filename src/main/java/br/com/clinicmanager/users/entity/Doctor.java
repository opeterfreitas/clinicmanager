package br.com.clinicmanager.users.entity;

import br.com.clinicmanager.clinics.entity.Clinic;
import br.com.clinicmanager.enums.Role;
import br.com.clinicmanager.users.dto.request.DoctorRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import br.com.clinicmanager.enums.Specialty;
import br.com.clinicmanager.enums.ProfessionalRegistryType;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Doctor extends User {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Specialty specialty;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProfessionalRegistryType professionalRegistryType;

    @Column(nullable = false)
    private String registryNumber;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private LocalDate registryValidity;

    public Doctor(String name, String cpf, String phoneNumber, String email, String password, Role role, Clinic clinic, Specialty specialty, ProfessionalRegistryType professionalRegistryType, String registryNumber, String state, LocalDate registryValidity, User createdBy) {
        super(name, cpf, phoneNumber, email, password, role, clinic, createdBy);
        this.specialty = specialty;
        this.professionalRegistryType = professionalRegistryType;
        this.registryNumber = registryNumber;
        this.state = state;
        this.registryValidity = registryValidity;
    }
    public Doctor(String name, String email, String password, Specialty specialty, String registrationNumber) {
        super(name, email, password);
        this.specialty = specialty;
    }
    public boolean isRegistryValid() {
        return registryValidity.isAfter(LocalDate.now());
    }
    public Doctor(DoctorRequestDTO dto, Clinic clinic, User createdBy) {
        super(dto.getName(), dto.getEmail(), dto.getPassword());
        this.specialty = dto.getSpecialty();
        this.registryNumber = dto.getRegistryNumber();
        this.setClinic(clinic);
        this.setCreatedBy(createdBy);
        this.setCreatedAt(LocalDateTime.now());
    }

    public Doctor(String cpf, String name, String phoneNumber, String email, String password, User createdBy, LocalDateTime createdAt, Specialty specialty, String registryNumber, ProfessionalRegistryType professionalRegistryType, String state, LocalDate registryValidity) {
        super(cpf, name, phoneNumber, email, password, createdBy, createdAt);
        this.specialty = specialty;
        this.registryNumber = registryNumber;
        this.professionalRegistryType = professionalRegistryType;
        this.state = state;
        this.registryValidity = registryValidity;
    }
}
