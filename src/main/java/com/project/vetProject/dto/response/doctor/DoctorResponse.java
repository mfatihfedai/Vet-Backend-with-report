package com.project.vetProject.dto.response.doctor;
import com.project.vetProject.entity.AvailableDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {
    private int id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
    private List<AvailableDate> availableDates;
}
