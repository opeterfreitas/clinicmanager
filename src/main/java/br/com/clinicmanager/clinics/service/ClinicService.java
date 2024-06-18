package br.com.clinicmanager.clinics.service;

import br.com.clinicmanager.clinics.dto.request.ClinicRequestDTO;
import br.com.clinicmanager.clinics.dto.response.ClinicResponseDTO;

import java.util.UUID;

public interface ClinicService {

    ClinicResponseDTO createClinic(ClinicRequestDTO clinicRequestDTO);

    ClinicResponseDTO getClinicById(UUID id);

    ClinicResponseDTO updateClinic(UUID id, ClinicRequestDTO clinicRequestDTO);

    void deleteClinic(UUID id);
}
