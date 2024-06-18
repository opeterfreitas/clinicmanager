package br.com.clinicmanager.notifications.dto.response;

import lombok.Data;
import br.com.clinicmanager.notifications.entity.Notification;

@Data
public class NotificationResponseDTO {

    private String id;
    private String user;
    private String type;
    private String message;
    private String scheduledTime;
    private String sentTime;
    private boolean success;

    public NotificationResponseDTO(Notification notification) {
        this.id = notification.getId().toString();
        this.user = notification.getUser().getName();
        this.type = notification.getType().name();
        this.message = notification.getMessage();
        this.scheduledTime = notification.getScheduledTime().toString();
        this.sentTime = notification.getSentTime() != null ? notification.getSentTime().toString() : null;
        this.success = notification.isSuccess();
    }
}
