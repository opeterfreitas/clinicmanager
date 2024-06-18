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
public class InvoiceReportDTO {
    private UUID id;
    private String clinicName;
    private String patientName;
    private LocalDateTime invoiceDate;
    private double amount;
    private String status;
}
