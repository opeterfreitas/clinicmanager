package br.com.clinicmanager.invoices.dto.response;

import lombok.Data;
import br.com.clinicmanager.invoices.entity.Invoice;

@Data
public class InvoiceResponseDTO {

    private String id;
    private String clinic;
    private String patient;
    private String amount;
    private String status;
    private String issueDate;
    private String dueDate;
    private String paymentDate;
    private String createdAt;
    private String updatedAt;

    public InvoiceResponseDTO(Invoice invoice) {
        this.id = invoice.getId().toString();
        this.clinic = invoice.getClinic().getName();
        this.patient = invoice.getPatient().getName();
        this.amount = invoice.getAmount().toString();
        this.status = invoice.getStatus().name();
        this.issueDate = invoice.getIssueDate().toString();
        this.dueDate = invoice.getDueDate().toString();
        this.paymentDate = invoice.getPaymentDate() != null ? invoice.getPaymentDate().toString() : null;
        this.createdAt = invoice.getCreatedAt().toString();
        this.updatedAt = invoice.getUpdatedAt().toString();
    }
}
