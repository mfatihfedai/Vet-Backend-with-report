package com.project.vetProject.dao;

import com.project.vetProject.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByDateTime(LocalDateTime localDateTime);
    Page<Appointment> findByDoctorNameContainingAndDateTimeBetween(String name, LocalDateTime entryDate, LocalDateTime exitDate, Pageable pageable);
    Page<Appointment> findByAnimalNameContainingAndDateTimeBetween(String name, LocalDateTime entryDate, LocalDateTime exitDate, Pageable pageable);
    Page<Appointment> findByDoctorIdAndDateTimeBetween(int id, LocalDateTime entryDate, LocalDateTime exitDate, Pageable pageable);
    Page<Appointment> findByAnimalIdAndDateTimeBetween(int id, LocalDateTime entryDate, LocalDateTime exitDate, Pageable pageable);
    Optional<Appointment> findByDateTimeAndDoctorIdAndAnimalId(LocalDateTime dateTime, Integer doctorId, Integer animalId);
}
