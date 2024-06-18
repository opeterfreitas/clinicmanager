package br.com.clinicmanager.reports.dto.response;

import br.com.clinicmanager.plans.enums.PlanType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PlanReportDTO {
    private UUID id;
    private String name;
    private PlanType type;
    private String description;
    private double price;
    private String duration;
}
