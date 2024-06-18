package br.com.clinicmanager.reports.dto.response;

import br.com.clinicmanager.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserReportDTO {
    private UUID id;
    private String name;
    private String email;
    private Role role;
    private String clinicName;
}
