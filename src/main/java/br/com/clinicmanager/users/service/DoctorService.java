package br.com.clinicmanager.users.service;

import br.com.clinicmanager.users.dto.request.DoctorRequestDTO;
import br.com.clinicmanager.users.dto.response.DoctorResponseDTO;

import java.util.List;
import java.util.UUID;

public interface DoctorService {
    DoctorResponseDTO createDoctor(DoctorRequestDTO doctorRequestDTO);

    List<DoctorResponseDTO> getDoctors();

    DoctorResponseDTO getDoctorById(UUID doctorId);

    DoctorResponseDTO updateDoctor(UUID doctorId, DoctorRequestDTO doctorRequestDTO);

    void deleteDoctor(UUID doctorId);
}
