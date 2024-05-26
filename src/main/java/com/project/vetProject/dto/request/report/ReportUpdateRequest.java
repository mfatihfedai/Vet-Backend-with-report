package com.project.vetProject.dto.request.report;

import com.project.vetProject.entity.Appointment;
import com.project.vetProject.entity.Vaccine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportUpdateRequest {
    private int id;
    private int price;
    private String description;
    private Appointment appointment;
}
