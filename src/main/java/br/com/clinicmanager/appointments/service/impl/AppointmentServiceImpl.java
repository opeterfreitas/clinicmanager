package br.com.clinicmanager.appointments.service.impl;

import br.com.clinicmanager.appointments.dto.request.AppointmentRequestDTO;
import br.com.clinicmanager.appointments.dto.response.AppointmentResponseDTO;
import br.com.clinicmanager.appointments.entity.Appointment;
import br.com.clinicmanager.appointments.repository.AppointmentRepository;
import br.com.clinicmanager.exception.ResourceNotFoundException;
import br.com.clinicmanager.appointments.service.AppointmentService;
import br.com.clinicmanager.users.repository.DoctorRepository;
import br.com.clinicmanager.users.repository.PatientRepository;
import br.com.clinicmanager.config.SecurityIdentityWrapper;
import br.com.clinicmanager.users.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class AppointmentServiceImpl implements AppointmentService {

    @Inject
    AppointmentRepository appointmentRepository;

    @Inject
    DoctorRepository doctorRepository;

    @Inject
    PatientRepository patientRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    SecurityIdentityWrapper securityIdentityWrapper;

    @Override
    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        var doctor = doctorRepository.findByIdOptional(appointmentRequestDTO.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
        var patient = patientRepository.findByIdOptional(appointmentRequestDTO.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));

        var createdBy = userRepository.findByEmail(securityIdentityWrapper.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário criador não encontrado"));

        Appointment appointment = appointmentRequestDTO.toEntity(doctor, patient, createdBy);
        appointmentRepository.persist(appointment);

        return new AppointmentResponseDTO(appointment);
    }

    @Override
    public List<AppointmentResponseDTO> getAppointments() {
        return appointmentRepository.listAll().stream()
                .map(Appointment::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentResponseDTO getAppointmentById(UUID appointmentId) {
        Appointment appointment = appointmentRepository.findByIdOptional(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada"));
        return appointment.toResponseDTO();
    }

    @Override
    public AppointmentResponseDTO updateAppointment(UUID appointmentId, AppointmentRequestDTO appointmentRequestDTO) {
        Appointment appointment = appointmentRepository.findByIdOptional(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada"));

        var doctor = doctorRepository.findByIdOptional(appointmentRequestDTO.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
        var patient = patientRepository.findByIdOptional(appointmentRequestDTO.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));

        var updatedBy = userRepository.findByEmail(securityIdentityWrapper.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário atualizador não encontrado"));

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(appointmentRequestDTO.getAppointmentDate());
        appointment.setStatus(appointmentRequestDTO.getStatus());
        appointment.setUpdatedBy(updatedBy);
        appointment.setUpdatedAt(LocalDateTime.now());

        appointmentRepository.persist(appointment);
        return appointment.toResponseDTO();
    }

    @Override
    public void deleteAppointment(UUID appointmentId) {
        Appointment appointment = appointmentRepository.findByIdOptional(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada"));
        appointmentRepository.delete(appointment);
    }
}
