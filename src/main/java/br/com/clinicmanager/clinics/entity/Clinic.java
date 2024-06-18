package br.com.clinicmanager.clinics.entity;

import br.com.clinicmanager.clinics.enums.ClinicStatus;
import br.com.clinicmanager.plans.entity.Plan;
import br.com.clinicmanager.users.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @Column(nullable = false)
    private String cnpj;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClinicStatus status;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    @Column
    private LocalDateTime updatedAt;

    public Clinic(String name, String address, String phoneNumber, String email, Plan plan, String cnpj, ClinicStatus status) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.plan = plan;
        this.cnpj = cnpj;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }
}
