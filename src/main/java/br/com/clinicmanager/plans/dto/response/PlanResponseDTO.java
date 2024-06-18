package br.com.clinicmanager.plans.dto.response;

import br.com.clinicmanager.plans.entity.Plan;
import br.com.clinicmanager.plans.enums.PlanType;
import lombok.Data;

import java.util.UUID;

@Data
public class PlanResponseDTO {

    private UUID id;
    private String name;
    private PlanType type;
    private String description;
    private Double price;
    private String duration;

    public PlanResponseDTO(Plan plan) {
        this.id = plan.getId();
        this.name = plan.getName();
        this.type = plan.getType();
        this.description = plan.getDescription();
        this.price = plan.getPrice();
        this.duration = plan.getDuration();
    }
}
