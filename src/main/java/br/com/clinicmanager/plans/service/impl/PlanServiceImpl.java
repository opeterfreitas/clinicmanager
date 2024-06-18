package br.com.clinicmanager.plans.service.impl;

import br.com.clinicmanager.exception.ResourceNotFoundException;
import br.com.clinicmanager.plans.dto.request.PlanRequestDTO;
import br.com.clinicmanager.plans.dto.response.PlanResponseDTO;
import br.com.clinicmanager.plans.entity.Plan;
import br.com.clinicmanager.plans.repository.PlanRepository;
import br.com.clinicmanager.plans.service.PlanService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class PlanServiceImpl implements PlanService {

    @Inject
    PlanRepository planRepository;

    @Override
    public PlanResponseDTO createPlan(PlanRequestDTO planRequestDTO) {
        Plan plan = new Plan(planRequestDTO);
        planRepository.persist(plan);
        return new PlanResponseDTO(plan);
    }

    @Override
    public PlanResponseDTO getPlanById(UUID planId) {
        Plan plan = planRepository.findByIdOptional(planId)
                .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado"));
        return new PlanResponseDTO(plan);
    }

    @Override
    public List<PlanResponseDTO> getAllPlans() {
        return planRepository.listAll().stream()
                .map(PlanResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public PlanResponseDTO updatePlan(UUID planId, PlanRequestDTO planRequestDTO) {
        Plan plan = planRepository.findByIdOptional(planId)
                .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado"));

        plan.setName(planRequestDTO.getName());
        plan.setDescription(planRequestDTO.getDescription());
        plan.setPrice(planRequestDTO.getPrice());
        plan.setDuration(planRequestDTO.getDuration());

        planRepository.persist(plan);
        return new PlanResponseDTO(plan);
    }

    @Override
    public void deletePlan(UUID planId) {
        Plan plan = planRepository.findByIdOptional(planId)
                .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado"));
        planRepository.delete(plan);
    }
}
