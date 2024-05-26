package com.project.vetProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "vaccines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_id", columnDefinition = "serial")
    private int id;

    @Column(name = "vaccine_name")
    private String name;
    @Column(name = "vaccine_code")
    private String code;
    @Column(name = "vaccine_protectionStrtDate")
    private LocalDate protectionStrtDate;
    @Column(name = "vaccine_protectionFnshDate")
    private LocalDate protectionFnshDate;

    @ManyToOne()
    @JoinColumn(name = "vaccine_animal_id", referencedColumnName = "animal_id")
    private Animal animal;

    @ManyToOne()
    @JoinColumn(name = "vaccine_report_id", referencedColumnName = "report_id")
    private Report report;
}
//
//spring.datasource.url=jdbc:postgresql://dpg-cp70o5mv3ddc73fs0b70-a.oregon-postgres.render.com/vetapps
//spring.datasource.username=vetapps_user
//spring.datasource.password=FNL864VYzDxOke8smz5CjfpZPCpRVmHw
//spring.datasource.driver-class-name=org.postgresql.Driver
//
//postgres://vetapps_user:FNL864VYzDxOke8smz5CjfpZPCpRVmHw@dpg-cp70o5mv3ddc73fs0b70-a.oregon-postgres.render.com/vetapps

