package com.project.vetProject.dao;

import com.project.vetProject.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepo extends JpaRepository<Report, Integer> {

}
