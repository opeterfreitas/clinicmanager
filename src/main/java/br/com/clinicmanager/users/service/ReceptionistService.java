package br.com.clinicmanager.users.service;

import br.com.clinicmanager.users.dto.request.ReceptionistRequestDTO;
import br.com.clinicmanager.users.dto.response.ReceptionistResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ReceptionistService {
    ReceptionistResponseDTO createReceptionist(ReceptionistRequestDTO receptionistRequestDTO);

    List<ReceptionistResponseDTO> getReceptionists();

    ReceptionistResponseDTO getReceptionistById(UUID receptionistId);

    ReceptionistResponseDTO updateReceptionist(UUID receptionistId, ReceptionistRequestDTO receptionistRequestDTO);

    void deleteReceptionist(UUID receptionistId);
}
