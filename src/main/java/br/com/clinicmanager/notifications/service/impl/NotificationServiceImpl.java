package br.com.clinicmanager.notifications.service.impl;

import br.com.clinicmanager.notifications.entity.Notification;
import br.com.clinicmanager.notifications.repository.NotificationRepository;
import br.com.clinicmanager.notifications.dto.request.NotificationRequestDTO;
import br.com.clinicmanager.notifications.dto.response.NotificationResponseDTO;
import br.com.clinicmanager.exception.ResourceNotFoundException;
import br.com.clinicmanager.notifications.service.NotificationService;
import br.com.clinicmanager.users.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class NotificationServiceImpl implements NotificationService {

    @Inject
    NotificationRepository notificationRepository;

    @Inject
    UserRepository userRepository;

    @Override
    public NotificationResponseDTO createNotification(NotificationRequestDTO notificationRequestDTO) {
        var user = userRepository.findByIdOptional(notificationRequestDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        Notification notification = notificationRequestDTO.toEntity(user);
        notificationRepository.persist(notification);
        return new NotificationResponseDTO(notification);
    }

    @Override
    public List<NotificationResponseDTO> getNotifications() {
        return notificationRepository.listAll().stream()
                .map(NotificationResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationResponseDTO getNotificationById(UUID notificationId) {
        Notification notification = notificationRepository.findByIdOptional(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notificação não encontrada"));
        return new NotificationResponseDTO(notification);
    }

    @Override
    public NotificationResponseDTO updateNotification(UUID notificationId, NotificationRequestDTO notificationRequestDTO) {
        Notification notification = notificationRepository.findByIdOptional(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notificação não encontrada"));

        var user = userRepository.findByIdOptional(notificationRequestDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        notification.setUser(user);
        notification.setType(notificationRequestDTO.getType());
        notification.setMessage(notificationRequestDTO.getMessage());
        notification.setScheduledTime(notificationRequestDTO.getScheduledTime());
        notification.setSentTime(notificationRequestDTO.getSentTime());
        notification.setSuccess(notificationRequestDTO.isSuccess());

        notificationRepository.persist(notification);
        return new NotificationResponseDTO(notification);
    }

    @Override
    public void deleteNotification(UUID notificationId) {
        Notification notification = notificationRepository.findByIdOptional(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notificação não encontrada"));
        notificationRepository.delete(notification);
    }
}
