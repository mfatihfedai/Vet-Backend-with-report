package com.project.vetProject.dao;

import com.project.vetProject.entity.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepo extends JpaRepository<Animal, Integer> {
    Page<Animal> findByNameContaining(String name, Pageable pageable);
    Page<Animal> findByCustomerNameContaining (String name, Pageable pageable);
    List<Animal> findByNameAndSpeciesAndBreedAndGender(String name,String species,String breed,String gender);
}
