package com.project.vetProject.business.abstracts;

import com.project.vetProject.core.result.ResultData;
import com.project.vetProject.dto.CursorResponse;
import com.project.vetProject.dto.request.availableDate.AvailableDateSaveRequest;
import com.project.vetProject.dto.request.availableDate.AvailableDateUpdateRequest;
import com.project.vetProject.dto.response.availableDate.AvailableDateResponse;
import com.project.vetProject.entity.AvailableDate;
import com.project.vetProject.entity.Doctor;
import org.springframework.data.domain.Page;

import java.util.List;


public interface IAvailableDateService {
    ResultData<AvailableDateResponse> save(AvailableDateSaveRequest availableDateSaveRequest);
    AvailableDate get(int id);
    ResultData<CursorResponse<AvailableDateResponse>> cursor(int page, int pageSize);
    ResultData<AvailableDateResponse> update(AvailableDateUpdateRequest availableDateUpdateRequest);
    boolean delete(int id);
    ResultData<AvailableDateResponse> getById(int id);
    ResultData<List<AvailableDateResponse>> findByDoctor(Doctor doctor);
}
