package br.com.clinicmanager.users.service.impl;

import br.com.clinicmanager.users.dto.request.PatientRequestDTO;
import br.com.clinicmanager.users.dto.response.PatientResponseDTO;
import br.com.clinicmanager.users.entity.Patient;
import br.com.clinicmanager.users.repository.PatientRepository;
import br.com.clinicmanager.exception.ResourceNotFoundException;
import br.com.clinicmanager.users.service.PatientService;
import br.com.clinicmanager.clinics.repository.ClinicRepository;
import br.com.clinicmanager.config.SecurityIdentityWrapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class PatientServiceImpl implements PatientService {

    @Inject
    PatientRepository patientRepository;

    @Inject
    ClinicRepository clinicRepository;

    @Inject
    SecurityIdentityWrapper securityIdentityWrapper;

    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        var clinic = clinicRepository.findByIdOptional(patientRequestDTO.getClinicId())
                .orElseThrow(() -> new ResourceNotFoundException("Clínica não encontrada"));

        var createdBy = patientRepository.findByEmail(securityIdentityWrapper.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário criador não encontrado"));

        Patient patient = new Patient(patientRequestDTO, clinic, createdBy);
        patient.setCreatedBy(createdBy);
        patient.setCreatedAt(LocalDateTime.now());
        patientRepository.persist(patient);

        return new PatientResponseDTO(patient);
    }

    @Override
    public List<PatientResponseDTO> getPatients() {
        return patientRepository.listAll().stream()
                .map(PatientResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public PatientResponseDTO getPatientById(UUID patientId) {
        Patient patient = patientRepository.findByIdOptional(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));
        return new PatientResponseDTO(patient);
    }

    @Override
    public PatientResponseDTO updatePatient(UUID patientId, PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.findByIdOptional(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));

        var clinic = clinicRepository.findByIdOptional(patientRequestDTO.getClinicId())
                .orElseThrow(() -> new ResourceNotFoundException("Clínica não encontrada"));

        var updatedBy = patientRepository.findByEmail(securityIdentityWrapper.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário atualizador não encontrado"));

        patient.setName(patientRequestDTO.getName());
        patient.setCpf(patientRequestDTO.getCpf());
        patient.setPhoneNumber(patientRequestDTO.getPhoneNumber());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setPassword(patientRequestDTO.getPassword());
        patient.setRole(patientRequestDTO.getRole());
        patient.setClinic(clinic);
        patient.setMedicalHistory(patientRequestDTO.getMedicalHistory());
        patient.setAllergies(patientRequestDTO.getAllergies());
        patient.setCurrentMedications(patientRequestDTO.getCurrentMedications());
        patient.setUpdatedBy(updatedBy);
        patient.setUpdatedAt(LocalDateTime.now());

        patientRepository.persist(patient);
        return new PatientResponseDTO(patient);
    }

    @Override
    public void deletePatient(UUID patientId) {
        Patient patient = patientRepository.findByIdOptional(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));
        patientRepository.delete(patient);
    }
}
