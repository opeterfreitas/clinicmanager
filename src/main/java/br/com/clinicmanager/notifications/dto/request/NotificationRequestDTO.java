package br.com.clinicmanager.notifications.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import br.com.clinicmanager.enums.NotificationType;
import br.com.clinicmanager.notifications.entity.Notification;
import br.com.clinicmanager.users.entity.User;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class NotificationRequestDTO {

    @NotNull(message = "Usuário é obrigatório")
    private UUID userId;

    @NotNull(message = "Tipo de notificação é obrigatório")
    private NotificationType type;

    @NotBlank(message = "Mensagem é obrigatória")
    private String message;

    @NotNull(message = "Horário agendado é obrigatório")
    private LocalDateTime scheduledTime;

    private LocalDateTime sentTime;

    private boolean success;

    public Notification toEntity(User user) {
        return new Notification(user, this.type, this.message, this.scheduledTime);
    }
}
