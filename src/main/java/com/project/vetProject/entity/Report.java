package com.project.vetProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "reports")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", columnDefinition = "serial")
    private int id;

    @Column(name = "report_price")
    private int price;

    @Column(name = "report_description")
    private String description;

    @OneToMany(mappedBy = "report",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Vaccine> vaccines;

    @OneToOne()
    @JoinColumn(name = "report_appointment_id", referencedColumnName = "appointment_id")
    private Appointment appointment;

}
