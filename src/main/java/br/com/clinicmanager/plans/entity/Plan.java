package br.com.clinicmanager.plans.entity;

import br.com.clinicmanager.plans.dto.request.PlanRequestDTO;
import br.com.clinicmanager.plans.enums.PlanType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlanType type;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String duration;

    public Plan(String name, PlanType type, String description, Double price, String duration) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
        this.duration = duration;
    }

    public Plan(PlanRequestDTO dto) {
        this.name = dto.getName();
        this.type = dto.getType();
        this.description = dto.getDescription();
        this.price = dto.getPrice();
        this.duration = dto.getDuration();
    }
}
