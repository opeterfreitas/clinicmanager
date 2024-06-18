package br.com.clinicmanager.reports.dto.response;

import br.com.clinicmanager.clinics.enums.ClinicStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ClinicReportDTO {
    private UUID id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String cnpj;
    private ClinicStatus status;
    private String planName;
}
