package br.com.clinicmanager.reports.service.impl;

import br.com.clinicmanager.reports.dto.response.ClinicReportDTO;
import br.com.clinicmanager.reports.dto.response.UserReportDTO;
import br.com.clinicmanager.reports.dto.response.AppointmentReportDTO;
import br.com.clinicmanager.reports.dto.response.NotificationReportDTO;
import br.com.clinicmanager.reports.dto.response.InvoiceReportDTO;
import br.com.clinicmanager.reports.dto.response.PlanReportDTO;
import br.com.clinicmanager.reports.repository.ReportsRepository;
import br.com.clinicmanager.reports.service.ReportService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ReportServiceImpl implements ReportService {

    @Inject
    ReportsRepository reportsRepository;

    @Override
    public List<ClinicReportDTO> generateClinicReport() {
        return reportsRepository.generateClinicReport();
    }

    @Override
    public List<UserReportDTO> generateUserReport() {
        return reportsRepository.generateUserReport();
    }

    @Override
    public List<AppointmentReportDTO> generateAppointmentReport() {
        return reportsRepository.generateAppointmentReport();
    }

    @Override
    public List<NotificationReportDTO> generateNotificationReport() {
        return reportsRepository.generateNotificationReport();
    }

    @Override
    public List<InvoiceReportDTO> generateInvoiceReport() {
        return reportsRepository.generateInvoiceReport();
    }

    @Override
    public List<PlanReportDTO> generatePlanReport() {
        return reportsRepository.generatePlanReport();
    }
}
