package br.com.clinicmanager.reports.service;

import br.com.clinicmanager.reports.dto.response.ClinicReportDTO;
import br.com.clinicmanager.reports.dto.response.UserReportDTO;
import br.com.clinicmanager.reports.dto.response.AppointmentReportDTO;
import br.com.clinicmanager.reports.dto.response.NotificationReportDTO;
import br.com.clinicmanager.reports.dto.response.InvoiceReportDTO;
import br.com.clinicmanager.reports.dto.response.PlanReportDTO;

import java.util.List;

public interface ReportService {

    List<ClinicReportDTO> generateClinicReport();

    List<UserReportDTO> generateUserReport();

    List<AppointmentReportDTO> generateAppointmentReport();

    List<NotificationReportDTO> generateNotificationReport();

    List<InvoiceReportDTO> generateInvoiceReport();

    List<PlanReportDTO> generatePlanReport();
}
