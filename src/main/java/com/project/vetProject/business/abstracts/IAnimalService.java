package com.project.vetProject.business.abstracts;

import com.project.vetProject.core.result.ResultData;
import com.project.vetProject.dto.CursorResponse;
import com.project.vetProject.dto.request.animal.AnimalSaveRequest;
import com.project.vetProject.dto.request.animal.AnimalUpdateRequest;
import com.project.vetProject.dto.response.animal.AnimalResponse;
import com.project.vetProject.entity.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IAnimalService {
    ResultData<AnimalResponse> save(AnimalSaveRequest animalSaveRequest);
    Animal get(int id);
    ResultData<CursorResponse<AnimalResponse>> cursor(int page, int pageSize);
    ResultData<List<AnimalResponse>> findByNameContaining(String name, int page, int pageSize);
    ResultData<List<AnimalResponse>> findByCustomerNameContaining(String name, int page, int pageSize);
    List<Animal> findByNameAndSpeciesAndBreedAndGender(String name,String species,String breed,String gender);
    ResultData<AnimalResponse> update(AnimalUpdateRequest animalUpdateRequest);
    boolean delete(int id);
    ResultData<AnimalResponse> getById(int id);
}
