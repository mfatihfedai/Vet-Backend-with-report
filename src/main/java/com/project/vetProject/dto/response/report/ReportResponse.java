package com.project.vetProject.dto.response.report;

import com.project.vetProject.entity.Appointment;
import com.project.vetProject.entity.Vaccine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponse {
    private int id;
    private int price;
    private String description;
    private Appointment appointment;
    private List<Vaccine> vaccines;
}
