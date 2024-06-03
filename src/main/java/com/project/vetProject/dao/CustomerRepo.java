package com.project.vetProject.dao;

import com.project.vetProject.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    Page<Customer> findByNameContainingIgnoreCase(String name, Pageable pageable);
    List<Customer> findByNameAndMailAndPhone(String name, String mail, String phone);
}
