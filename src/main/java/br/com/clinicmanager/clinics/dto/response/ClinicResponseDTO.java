package br.com.clinicmanager.clinics.dto.response;

import br.com.clinicmanager.clinics.entity.Clinic;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ClinicResponseDTO {

    private UUID id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private UUID planId;
    private String cnpj;
    private String status;
    private UUID createdBy;
    private LocalDateTime createdAt;
    private UUID updatedBy;
    private LocalDateTime updatedAt;

    public ClinicResponseDTO(Clinic clinic) {
        this.id = clinic.getId();
        this.name = clinic.getName();
        this.address = clinic.getAddress();
        this.phoneNumber = clinic.getPhoneNumber();
        this.email = clinic.getEmail();
        this.planId = clinic.getPlan() != null ? clinic.getPlan().getId() : null;
        this.cnpj = clinic.getCnpj();
        this.status = clinic.getStatus().name();
        this.createdBy = clinic.getCreatedBy() != null ? clinic.getCreatedBy().getId() : null;
        this.createdAt = clinic.getCreatedAt();
        this.updatedBy = clinic.getUpdatedBy() != null ? clinic.getUpdatedBy().getId() : null;
        this.updatedAt = clinic.getUpdatedAt();
    }
}
