package br.com.clinicmanager.reports.repository;

import br.com.clinicmanager.reports.dto.response.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@ApplicationScoped
public class ReportsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ClinicReportDTO> generateClinicReport() {
        String jpql = "SELECT new br.com.clinicmanager.reports.dto.ClinicReportDTO(c.id, c.name, c.address, c.phoneNumber, c.email, c.cnpj, c.status, p.name) " +
                "FROM Clinic c JOIN c.plan p";
        TypedQuery<ClinicReportDTO> query = entityManager.createQuery(jpql, ClinicReportDTO.class);
        return query.getResultList();
    }

    public List<UserReportDTO> generateUserReport() {
        String jpql = "SELECT new br.com.clinicmanager.reports.dto.UserReportDTO(u.id, u.name, u.email, u.role, c.name) " +
                "FROM User u JOIN u.clinic c";
        TypedQuery<UserReportDTO> query = entityManager.createQuery(jpql, UserReportDTO.class);
        return query.getResultList();
    }

    public List<AppointmentReportDTO> generateAppointmentReport() {
        String jpql = "SELECT new br.com.clinicmanager.reports.dto.AppointmentReportDTO(a.id, a.appointmentDate, a.status, d.name, p.name, c.name) " +
                "FROM Appointment a JOIN a.doctor d JOIN a.patient p JOIN a.clinic c";
        TypedQuery<AppointmentReportDTO> query = entityManager.createQuery(jpql, AppointmentReportDTO.class);
        return query.getResultList();
    }

    public List<NotificationReportDTO> generateNotificationReport() {
        String jpql = "SELECT new br.com.clinicmanager.reports.dto.NotificationReportDTO(n.id, n.message, n.sendDate, n.recipientType, n.recipientContact, n.notificationType) " +
                "FROM Notification n";
        TypedQuery<NotificationReportDTO> query = entityManager.createQuery(jpql, NotificationReportDTO.class);
        return query.getResultList();
    }

    public List<InvoiceReportDTO> generateInvoiceReport() {
        String jpql = "SELECT new br.com.clinicmanager.reports.dto.InvoiceReportDTO(i.id, c.name, p.name, i.invoiceDate, i.amount, i.status) " +
                "FROM Invoice i JOIN i.clinic c JOIN i.patient p";
        TypedQuery<InvoiceReportDTO> query = entityManager.createQuery(jpql, InvoiceReportDTO.class);
        return query.getResultList();
    }

    public List<PlanReportDTO> generatePlanReport() {
        String jpql = "SELECT new br.com.clinicmanager.reports.dto.PlanReportDTO(p.id, p.name, p.type, p.description, p.price, p.duration) " +
                "FROM Plan p";
        TypedQuery<PlanReportDTO> query = entityManager.createQuery(jpql, PlanReportDTO.class);
        return query.getResultList();
    }
}
