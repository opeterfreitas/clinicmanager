package br.com.clinicmanager.invoices.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import br.com.clinicmanager.enums.InvoiceStatus;
import br.com.clinicmanager.invoices.entity.Invoice;
import br.com.clinicmanager.clinics.entity.Clinic;
import br.com.clinicmanager.users.entity.Patient;
import br.com.clinicmanager.users.entity.User;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class InvoiceRequestDTO {

    @NotNull(message = "Clínica é obrigatória")
    private UUID clinicId;

    @NotNull(message = "Paciente é obrigatório")
    private UUID patientId;

    @NotNull(message = "Valor é obrigatório")
    private BigDecimal amount;

    @NotNull(message = "Status é obrigatório")
    private InvoiceStatus status;

    @NotNull(message = "Data de emissão é obrigatória")
    private LocalDateTime issueDate;

    @NotNull(message = "Data de vencimento é obrigatória")
    private LocalDateTime dueDate;

    private LocalDateTime paymentDate;

    public Invoice toEntity(Clinic clinic, Patient patient, User createdBy) {
        return new Invoice(clinic, patient, this.amount, this.status, this.issueDate, this.dueDate, this.paymentDate, createdBy);
    }
}
