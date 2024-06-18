package br.com.clinicmanager.reports.controller;

import br.com.clinicmanager.reports.dto.response.ClinicReportDTO;
import br.com.clinicmanager.reports.dto.response.UserReportDTO;
import br.com.clinicmanager.reports.dto.response.AppointmentReportDTO;
import br.com.clinicmanager.reports.dto.response.NotificationReportDTO;
import br.com.clinicmanager.reports.dto.response.InvoiceReportDTO;
import br.com.clinicmanager.reports.dto.response.PlanReportDTO;
import br.com.clinicmanager.reports.service.ReportService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/reports")
@Produces(MediaType.APPLICATION_JSON)
public class ReportController {

    @Inject
    ReportService reportService;

    @GET
    @Path("/clinics")
    @RolesAllowed({"ADMIN", "OWNER"})
    public List<ClinicReportDTO> getClinicReport() {
        return reportService.generateClinicReport();
    }

    @GET
    @Path("/users")
    @RolesAllowed({"ADMIN", "OWNER"})
    public List<UserReportDTO> getUserReport() {
        return reportService.generateUserReport();
    }

    @GET
    @Path("/appointments")
    @RolesAllowed({"ADMIN", "OWNER"})
    public List<AppointmentReportDTO> getAppointmentReport() {
        return reportService.generateAppointmentReport();
    }

    @GET
    @Path("/notifications")
    @RolesAllowed({"ADMIN", "OWNER"})
    public List<NotificationReportDTO> getNotificationReport() {
        return reportService.generateNotificationReport();
    }

    @GET
    @Path("/invoices")
    @RolesAllowed({"ADMIN", "OWNER"})
    public List<InvoiceReportDTO> getInvoiceReport() {
        return reportService.generateInvoiceReport();
    }

    @GET
    @Path("/plans")
    @RolesAllowed({"ADMIN", "OWNER"})
    public List<PlanReportDTO> getPlanReport() {
        return reportService.generatePlanReport();
    }
}
