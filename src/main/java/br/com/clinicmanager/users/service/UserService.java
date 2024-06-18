package br.com.clinicmanager.users.service;

import br.com.clinicmanager.users.dto.request.UserRequestDTO;
import br.com.clinicmanager.users.dto.response.UserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);

    List<UserResponseDTO> getUsers();

    UserResponseDTO getUserById(UUID userId);

    UserResponseDTO updateUser(UUID userId, UserRequestDTO userRequestDTO);

    void deleteUser(UUID userId);
}
