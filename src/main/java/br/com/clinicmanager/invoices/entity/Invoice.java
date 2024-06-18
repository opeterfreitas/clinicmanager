package br.com.clinicmanager.invoices.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import br.com.clinicmanager.enums.InvoiceStatus;
import br.com.clinicmanager.clinics.entity.Clinic;
import br.com.clinicmanager.users.entity.Patient;
import br.com.clinicmanager.users.entity.User;

@Entity
@Data
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    private Clinic clinic;

    @ManyToOne
    private Patient patient;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    private LocalDateTime issueDate;
    private LocalDateTime dueDate;
    private LocalDateTime paymentDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private User updatedBy;

    public Invoice(Clinic clinic, Patient patient, BigDecimal amount, InvoiceStatus status, LocalDateTime issueDate, LocalDateTime dueDate, LocalDateTime paymentDate, User createdBy) {
        this.clinic = clinic;
        this.patient = patient;
        this.amount = amount;
        this.status = status;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
        this.createdBy = createdBy;
        this.updatedBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
