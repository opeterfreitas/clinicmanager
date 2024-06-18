package br.com.clinicmanager.users.service.impl;

import br.com.clinicmanager.users.dto.request.ReceptionistRequestDTO;
import br.com.clinicmanager.users.dto.response.ReceptionistResponseDTO;
import br.com.clinicmanager.users.entity.Receptionist;
import br.com.clinicmanager.users.repository.ReceptionistRepository;
import br.com.clinicmanager.exception.ResourceNotFoundException;
import br.com.clinicmanager.users.service.ReceptionistService;
import br.com.clinicmanager.clinics.repository.ClinicRepository;
import br.com.clinicmanager.config.SecurityIdentityWrapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class ReceptionistServiceImpl implements ReceptionistService {

    @Inject
    ReceptionistRepository receptionistRepository;

    @Inject
    ClinicRepository clinicRepository;

    @Inject
    SecurityIdentityWrapper securityIdentityWrapper;

    @Override
    public ReceptionistResponseDTO createReceptionist(ReceptionistRequestDTO receptionistRequestDTO) {
        var clinic = clinicRepository.findByIdOptional(receptionistRequestDTO.getClinicId())
                .orElseThrow(() -> new ResourceNotFoundException("Clínica não encontrada"));

        var createdBy = receptionistRepository.findByEmail(securityIdentityWrapper.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário criador não encontrado"));

        Receptionist receptionist = new Receptionist(receptionistRequestDTO, clinic, createdBy);
        receptionist.setCreatedBy(createdBy);
        receptionist.setCreatedAt(LocalDateTime.now());
        receptionistRepository.persist(receptionist);

        return new ReceptionistResponseDTO(receptionist);
    }

    @Override
    public List<ReceptionistResponseDTO> getReceptionists() {
        return receptionistRepository.listAll().stream()
                .map(ReceptionistResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public ReceptionistResponseDTO getReceptionistById(UUID receptionistId) {
        Receptionist receptionist = receptionistRepository.findByIdOptional(receptionistId)
                .orElseThrow(() -> new ResourceNotFoundException("Recepcionista não encontrado"));
        return new ReceptionistResponseDTO(receptionist);
    }

    @Override
    public ReceptionistResponseDTO updateReceptionist(UUID receptionistId, ReceptionistRequestDTO receptionistRequestDTO) {
        Receptionist receptionist = receptionistRepository.findByIdOptional(receptionistId)
                .orElseThrow(() -> new ResourceNotFoundException("Recepcionista não encontrado"));

        var clinic = clinicRepository.findByIdOptional(receptionistRequestDTO.getClinicId())
                .orElseThrow(() -> new ResourceNotFoundException("Clínica não encontrada"));

        var updatedBy = receptionistRepository.findByEmail(securityIdentityWrapper.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário atualizador não encontrado"));

        receptionist.setName(receptionistRequestDTO.getName());
        receptionist.setCpf(receptionistRequestDTO.getCpf());
        receptionist.setPhoneNumber(receptionistRequestDTO.getPhoneNumber());
        receptionist.setEmail(receptionistRequestDTO.getEmail());
        receptionist.setPassword(receptionistRequestDTO.getPassword());
        receptionist.setRole(receptionistRequestDTO.getRole());
        receptionist.setClinic(clinic);
        receptionist.setShift(receptionistRequestDTO.getShift());
        receptionist.setUpdatedBy(updatedBy);
        receptionist.setUpdatedAt(LocalDateTime.now());

        receptionistRepository.persist(receptionist);
        return new ReceptionistResponseDTO(receptionist);
    }

    @Override
    public void deleteReceptionist(UUID receptionistId) {
        Receptionist receptionist = receptionistRepository.findByIdOptional(receptionistId)
                .orElseThrow(() -> new ResourceNotFoundException("Recepcionista não encontrado"));
        receptionistRepository.delete(receptionist);
    }
}
