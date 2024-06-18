package br.com.clinicmanager.plans.service;

import br.com.clinicmanager.plans.dto.request.PlanRequestDTO;
import br.com.clinicmanager.plans.dto.response.PlanResponseDTO;

import java.util.List;
import java.util.UUID;

public interface PlanService {
    PlanResponseDTO createPlan(PlanRequestDTO planRequestDTO);
    PlanResponseDTO getPlanById(UUID planId);
    List<PlanResponseDTO> getAllPlans();
    PlanResponseDTO updatePlan(UUID planId, PlanRequestDTO planRequestDTO);
    void deletePlan(UUID planId);
}
