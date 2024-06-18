package br.com.clinicmanager.notifications.service;

import br.com.clinicmanager.notifications.dto.request.NotificationRequestDTO;
import br.com.clinicmanager.notifications.dto.response.NotificationResponseDTO;

import java.util.List;
import java.util.UUID;

public interface NotificationService {
    NotificationResponseDTO createNotification(NotificationRequestDTO notificationRequestDTO);

    List<NotificationResponseDTO> getNotifications();

    NotificationResponseDTO getNotificationById(UUID notificationId);

    NotificationResponseDTO updateNotification(UUID notificationId, NotificationRequestDTO notificationRequestDTO);

    void deleteNotification(UUID notificationId);
}
