package br.com.clinicmanager.users.service.impl;

import br.com.clinicmanager.users.dto.request.DoctorRequestDTO;
import br.com.clinicmanager.users.dto.response.DoctorResponseDTO;
import br.com.clinicmanager.users.entity.Doctor;
import br.com.clinicmanager.users.repository.DoctorRepository;
import br.com.clinicmanager.exception.ResourceNotFoundException;
import br.com.clinicmanager.users.service.DoctorService;
import br.com.clinicmanager.clinics.repository.ClinicRepository;
import br.com.clinicmanager.config.SecurityIdentityWrapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class DoctorServiceImpl implements DoctorService {

    @Inject
    DoctorRepository doctorRepository;

    @Inject
    ClinicRepository clinicRepository;

    @Inject
    SecurityIdentityWrapper securityIdentityWrapper;

    @Override
    public DoctorResponseDTO createDoctor(DoctorRequestDTO doctorRequestDTO) {
        var clinic = clinicRepository.findByIdOptional(doctorRequestDTO.getClinicId())
                .orElseThrow(() -> new ResourceNotFoundException("Clínica não encontrada"));

        var createdBy = doctorRepository.findByEmail(securityIdentityWrapper.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário criador não encontrado"));

        Doctor doctor = new Doctor(doctorRequestDTO, clinic, createdBy);
        doctor.setCreatedBy(createdBy);
        doctor.setCreatedAt(LocalDateTime.now());
        doctorRepository.persist(doctor);

        return new DoctorResponseDTO(doctor);
    }

    @Override
    public List<DoctorResponseDTO> getDoctors() {
        return doctorRepository.listAll().stream()
                .map(DoctorResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorResponseDTO getDoctorById(UUID doctorId) {
        Doctor doctor = doctorRepository.findByIdOptional(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
        return new DoctorResponseDTO(doctor);
    }

    @Override
    public DoctorResponseDTO updateDoctor(UUID doctorId, DoctorRequestDTO doctorRequestDTO) {
        Doctor doctor = doctorRepository.findByIdOptional(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));

        var clinic = clinicRepository.findByIdOptional(doctorRequestDTO.getClinicId())
                .orElseThrow(() -> new ResourceNotFoundException("Clínica não encontrada"));

        var updatedBy = doctorRepository.findByEmail(securityIdentityWrapper.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário atualizador não encontrado"));

        doctor.setName(doctorRequestDTO.getName());
        doctor.setCpf(doctorRequestDTO.getCpf());
        doctor.setPhoneNumber(doctorRequestDTO.getPhoneNumber());
        doctor.setEmail(doctorRequestDTO.getEmail());
        doctor.setPassword(doctorRequestDTO.getPassword());
        doctor.setRole(doctorRequestDTO.getRole());
        doctor.setClinic(clinic);
        doctor.setSpecialty(doctorRequestDTO.getSpecialty());
        doctor.setProfessionalRegistryType(doctorRequestDTO.getProfessionalRegistryType());
        doctor.setRegistryNumber(doctorRequestDTO.getRegistryNumber());
        doctor.setState(doctorRequestDTO.getState());
        doctor.setRegistryValidity(doctorRequestDTO.getRegistryValidity());
        doctor.setUpdatedBy(updatedBy);
        doctor.setUpdatedAt(LocalDateTime.now());

        doctorRepository.persist(doctor);
        return new DoctorResponseDTO(doctor);
    }

    @Override
    public void deleteDoctor(UUID doctorId) {
        Doctor doctor = doctorRepository.findByIdOptional(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
        doctorRepository.delete(doctor);
    }
}
