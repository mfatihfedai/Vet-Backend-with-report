package com.project.vetProject.business.concretes;

import com.project.vetProject.business.abstracts.IAvailableDateService;
import com.project.vetProject.business.abstracts.IDoctorService;
import com.project.vetProject.core.config.ConvertEntityToResponse;
import com.project.vetProject.core.config.modelMapper.IModelMapperService;
import com.project.vetProject.core.exception.DataAlreadyExistException;
import com.project.vetProject.core.exception.NotFoundException;
import com.project.vetProject.core.result.ResultData;
import com.project.vetProject.core.utilies.Msg;
import com.project.vetProject.core.utilies.ResultHelper;
import com.project.vetProject.dao.AvailableDateRepo;
import com.project.vetProject.dto.CursorResponse;
import com.project.vetProject.dto.request.availableDate.AvailableDateSaveRequest;
import com.project.vetProject.dto.request.availableDate.AvailableDateUpdateRequest;
import com.project.vetProject.dto.response.animal.AnimalResponse;
import com.project.vetProject.dto.response.availableDate.AvailableDateResponse;
import com.project.vetProject.entity.Animal;
import com.project.vetProject.entity.AvailableDate;
import com.project.vetProject.entity.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailableDateManager implements IAvailableDateService {
    private final ConvertEntityToResponse<AvailableDate, AvailableDateResponse> convert;
    private final AvailableDateRepo availableDateRepo;
    private final IModelMapperService modelMapperService;

    @Override
    public ResultData<AvailableDateResponse> save(AvailableDateSaveRequest availableDateSaveRequest) {
        List<AvailableDate> availableDateList = this.availableDateRepo.findByDateAndDoctor(
                availableDateSaveRequest.getDate(),
                availableDateSaveRequest.getDoctor());
        if (!availableDateList.isEmpty()){
            throw new DataAlreadyExistException(Msg.getEntityForMsg(AvailableDate.class));
        }
        AvailableDate saveAvailableDate = this.modelMapperService.forRequest().map(availableDateSaveRequest, AvailableDate.class);
        return ResultHelper.created(this.modelMapperService.forResponse().map(this.availableDateRepo.save(saveAvailableDate), AvailableDateResponse.class));
    }

    @Override
    public AvailableDate get(int id) {
        return this.availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public ResultData<CursorResponse<AvailableDateResponse>> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<AvailableDate> availableDatePage = this.availableDateRepo.findAll(pageable);
        Page<AvailableDateResponse> availableDateResponsePage = availableDatePage.map(availableDate -> this.modelMapperService.forResponse().map(availableDate, AvailableDateResponse.class));
        return ResultHelper.cursor(availableDateResponsePage);
    }

    @Override
    public ResultData<AvailableDateResponse> update(AvailableDateUpdateRequest availableDateUpdateRequest) {
        this.get(availableDateUpdateRequest.getId());
        AvailableDate updateAvailableDate = this.modelMapperService.forRequest().map(availableDateUpdateRequest, AvailableDate.class);
        return ResultHelper.success(this.modelMapperService.forResponse().map(this.availableDateRepo.save(updateAvailableDate), AvailableDateResponse.class));
    }

    @Override
    public boolean delete(int id) {
        AvailableDate availableDate = this.get(id);
        this.availableDateRepo.delete(availableDate);
        return true;
    }

    @Override
    public ResultData<AvailableDateResponse> getById(int id) {
        AvailableDate availableDate = this.get(id);
        AvailableDateResponse updateAvailableDate = modelMapperService.forRequest().map(availableDate, AvailableDateResponse.class);
        return ResultHelper.success(this.modelMapperService.forResponse().map(updateAvailableDate, AvailableDateResponse.class));
    }

    @Override
    public ResultData<List<AvailableDateResponse>> findByDoctor(Doctor doctor) {

        List<AvailableDate> availableDateList = this.availableDateRepo.findByDoctor(doctor);
        if(availableDateList.isEmpty()){
            return ResultHelper.error("Doctor is not found");
        }
        List<AvailableDateResponse> availableDateResponseList = this.convert.convertToResponseList(availableDateList, AvailableDateResponse.class);
        return ResultHelper.success(availableDateResponseList);
    }
}
