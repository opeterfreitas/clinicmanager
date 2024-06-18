package br.com.clinicmanager.plans.dto.request;

import br.com.clinicmanager.plans.entity.Plan;
import br.com.clinicmanager.plans.enums.PlanType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlanRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Type is required")
    private PlanType type;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    private Double price;

    @NotBlank(message = "Duration is required")
    private String duration;

    public Plan toEntity() {
        return new Plan(this);
    }
}
