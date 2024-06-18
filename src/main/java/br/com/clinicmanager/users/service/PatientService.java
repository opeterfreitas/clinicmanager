package br.com.clinicmanager.users.service;

import br.com.clinicmanager.users.dto.request.PatientRequestDTO;
import br.com.clinicmanager.users.dto.response.PatientResponseDTO;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO);

    List<PatientResponseDTO> getPatients();

    PatientResponseDTO getPatientById(UUID patientId);

    PatientResponseDTO updatePatient(UUID patientId, PatientRequestDTO patientRequestDTO);

    void deletePatient(UUID patientId);
}
