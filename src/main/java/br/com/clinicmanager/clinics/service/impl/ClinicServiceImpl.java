package br.com.clinicmanager.clinics.service.impl;

import br.com.clinicmanager.clinics.dto.request.ClinicRequestDTO;
import br.com.clinicmanager.clinics.dto.response.ClinicResponseDTO;
import br.com.clinicmanager.clinics.entity.Clinic;
import br.com.clinicmanager.clinics.repository.ClinicRepository;
import br.com.clinicmanager.clinics.service.ClinicService;
import br.com.clinicmanager.exception.ResourceNotFoundException;
import br.com.clinicmanager.plans.repository.PlanRepository;
import br.com.clinicmanager.users.repository.UserRepository;
import br.com.clinicmanager.config.SecurityIdentityWrapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@ApplicationScoped
public class ClinicServiceImpl implements ClinicService {

    @Inject
    ClinicRepository clinicRepository;

    @Inject
    PlanRepository planRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    SecurityIdentityWrapper securityIdentityWrapper;

    @Override
    @Transactional
    public ClinicResponseDTO createClinic(ClinicRequestDTO clinicRequestDTO) {
        var plan = planRepository.findByIdOptional(clinicRequestDTO.getPlanId())
                .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado"));

        var responsible = userRepository.findByIdOptional(clinicRequestDTO.getResponsibleUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário responsável não encontrado"));

        var createdBy = userRepository.findByEmail(securityIdentityWrapper.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário criador não encontrado"));

        Clinic clinic = clinicRequestDTO.toEntity(plan, responsible, createdBy);
        clinicRepository.persist(clinic);

        return new ClinicResponseDTO(clinic);
    }

    @Override
    public ClinicResponseDTO getClinicById(UUID id) {
        Clinic clinic = clinicRepository.findByIdOptional(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clínica não encontrada"));
        return new ClinicResponseDTO(clinic);
    }

    @Override
    @Transactional
    public ClinicResponseDTO updateClinic(UUID id, ClinicRequestDTO clinicRequestDTO) {
        Clinic clinic = clinicRepository.findByIdOptional(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clínica não encontrada"));

        var plan = planRepository.findByIdOptional(clinicRequestDTO.getPlanId())
                .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado"));

        var responsible = userRepository.findByIdOptional(clinicRequestDTO.getResponsibleUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário responsável não encontrado"));

        clinic.setName(clinicRequestDTO.getName());
        clinic.setAddress(clinicRequestDTO.getAddress());
        clinic.setPhoneNumber(clinicRequestDTO.getPhoneNumber());
        clinic.setEmail(clinicRequestDTO.getEmail());
        clinic.setPlan(plan);
        clinic.setCnpj(clinicRequestDTO.getCnpj());
        clinic.setStatus(clinicRequestDTO.getStatus());
        clinic.setUpdatedBy(responsible);
        clinic.setUpdatedAt(LocalDateTime.now());

        clinicRepository.persist(clinic);
        return new ClinicResponseDTO(clinic);
    }

    @Override
    @Transactional
    public void deleteClinic(UUID id) {
        Clinic clinic = clinicRepository.findByIdOptional(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clínica não encontrada"));
        clinicRepository.delete(clinic);
    }
}
