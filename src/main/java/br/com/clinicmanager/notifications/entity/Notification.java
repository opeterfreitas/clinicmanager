package br.com.clinicmanager.notifications.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import br.com.clinicmanager.users.entity.User;
import br.com.clinicmanager.enums.NotificationType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private String message;

    private LocalDateTime scheduledTime;
    private LocalDateTime sentTime;

    private boolean success;

    public Notification(User user, NotificationType type, String message, LocalDateTime scheduledTime) {
        this.user = user;
        this.type = type;
        this.message = message;
        this.scheduledTime = scheduledTime;
        this.sentTime = null;
        this.success = false;
    }
}
