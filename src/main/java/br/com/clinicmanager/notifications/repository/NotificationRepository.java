package br.com.clinicmanager.notifications.repository;

import br.com.clinicmanager.notifications.entity.Notification;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class NotificationRepository implements PanacheRepositoryBase<Notification, UUID> {

    public Optional<Notification> findByIdOptional(UUID id) {
        return find("id", id).firstResultOptional();
    }

    public List<Notification> findByUserId(UUID userId) {
        return list("user.id", userId);
    }

    public List<Notification> findByType(String type) {
        return list("type", type);
    }

    public List<Notification> findAllNotifications() {
        return listAll();
    }
}
