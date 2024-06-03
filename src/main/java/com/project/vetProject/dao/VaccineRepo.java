package com.project.vetProject.dao;

import com.project.vetProject.entity.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccineRepo extends JpaRepository<Vaccine, Integer> {
    List<Vaccine> findByAnimalId(int id);
    Page<Vaccine> findByprotectionFnshDateBetween(LocalDate entryDate, LocalDate exitDate, Pageable pageable);
    List<Vaccine> findByCodeAndName(String code, String name);
}
