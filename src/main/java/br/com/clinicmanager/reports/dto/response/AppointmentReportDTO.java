package br.com.clinicmanager.reports.dto.response;

import br.com.clinicmanager.enums.AppointmentStatus;
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
public class AppointmentReportDTO {
    private UUID id;
    private LocalDateTime appointmentDate;
    private AppointmentStatus status;
    private String doctorName;
    private String patientName;
    private String clinicName;
}
