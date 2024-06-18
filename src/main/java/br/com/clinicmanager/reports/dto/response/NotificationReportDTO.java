package br.com.clinicmanager.reports.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class NotificationReportDTO {
    private UUID id;
    private String message;
    private LocalDateTime sendDate;
    private String recipientType;
    private String recipientContact;
    private String notificationType;
}
