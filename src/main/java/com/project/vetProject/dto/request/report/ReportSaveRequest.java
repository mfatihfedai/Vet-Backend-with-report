package com.project.vetProject.dto.request.report;

import com.project.vetProject.entity.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportSaveRequest {
    private int price;
    private String description;
    private Appointment appointment;
}
