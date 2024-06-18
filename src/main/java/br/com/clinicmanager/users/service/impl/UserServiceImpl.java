package br.com.clinicmanager.users.service.impl;

import br.com.clinicmanager.users.dto.request.UserRequestDTO;
import br.com.clinicmanager.users.dto.response.UserResponseDTO;
import br.com.clinicmanager.users.entity.User;
import br.com.clinicmanager.users.repository.UserRepository;
import br.com.clinicmanager.exception.ResourceNotFoundException;
import br.com.clinicmanager.users.service.UserService;
import br.com.clinicmanager.clinics.repository.ClinicRepository;
import br.com.clinicmanager.config.SecurityIdentityWrapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    ClinicRepository clinicRepository;

    @Inject
    SecurityIdentityWrapper securityIdentityWrapper;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        var clinic = clinicRepository.findByIdOptional(userRequestDTO.getClinicId())
                .orElseThrow(() -> new ResourceNotFoundException("Clínica não encontrada"));

        User createdBy = userRepository.findByEmail(securityIdentityWrapper.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário criador não encontrado"));

        User user = new User(userRequestDTO, clinic, createdBy);
        user.setCreatedBy(createdBy);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.persist(user);

        return new UserResponseDTO(user);
    }

    @Override
    public List<UserResponseDTO> getUsers() {
        return userRepository.listAll().stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(UUID userId) {
        User user = userRepository.findByIdOptional(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return new UserResponseDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(UUID userId, UserRequestDTO userRequestDTO) {
        User user = userRepository.findByIdOptional(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        var clinic = clinicRepository.findByIdOptional(userRequestDTO.getClinicId())
                .orElseThrow(() -> new ResourceNotFoundException("Clínica não encontrada"));

        User updatedBy = userRepository.findByEmail(securityIdentityWrapper.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário atualizador não encontrado"));

        user.setName(userRequestDTO.getName());
        user.setCpf(userRequestDTO.getCpf());
        user.setPhoneNumber(userRequestDTO.getPhoneNumber());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setRole(userRequestDTO.getRole());
        user.setClinic(clinic);
        user.setUpdatedBy(updatedBy);
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.persist(user);
        return new UserResponseDTO(user);
    }

    @Override
    public void deleteUser(UUID userId) {
        User user = userRepository.findByIdOptional(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        userRepository.delete(user);
    }
}
