package br.com.clinicmanager.invoices.repository;

import br.com.clinicmanager.invoices.entity.Invoice;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class InvoiceRepository implements PanacheRepository<Invoice> {

    public Optional<Invoice> findById(UUID id) {
        return find("id", id).firstResultOptional();
    }
}
