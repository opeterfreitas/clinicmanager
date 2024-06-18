package br.com.clinicmanager.appointments.service;

import br.com.clinicmanager.appointments.dto.request.AppointmentRequestDTO;
import br.com.clinicmanager.appointments.dto.response.AppointmentResponseDTO;

import java.util.List;
import java.util.UUID;

public interface AppointmentService {
    AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentRequestDTO);

    List<AppointmentResponseDTO> getAppointments();

    AppointmentResponseDTO getAppointmentById(UUID appointmentId);

    AppointmentResponseDTO updateAppointment(UUID appointmentId, AppointmentRequestDTO appointmentRequestDTO);

    void deleteAppointment(UUID appointmentId);
}
