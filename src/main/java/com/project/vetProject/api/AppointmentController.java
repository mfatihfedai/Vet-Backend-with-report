package com.project.vetProject.api;

import com.project.vetProject.business.abstracts.IAnimalService;
import com.project.vetProject.business.abstracts.IAppointmentService;
import com.project.vetProject.business.abstracts.IDoctorService;
import com.project.vetProject.core.config.modelMapper.IModelMapperService;
import com.project.vetProject.core.result.Result;
import com.project.vetProject.core.result.ResultData;
import com.project.vetProject.core.utilies.ResultHelper;
import com.project.vetProject.dto.CursorResponse;
import com.project.vetProject.dto.request.appointment.AppointmentSaveRequest;
import com.project.vetProject.dto.request.appointment.AppointmentUpdateRequest;
import com.project.vetProject.dto.response.appointment.AppointmentResponse;
import com.project.vetProject.entity.Animal;
import com.project.vetProject.entity.Appointment;
import com.project.vetProject.entity.Doctor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final IAppointmentService appointmentService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest){
        return this.appointmentService.save(appointmentSaveRequest);
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AppointmentResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize){
        return this.appointmentService.cursor(page, pageSize);
    }
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest){
        return this.appointmentService.update(appointmentUpdateRequest);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable int id){
        this.appointmentService.delete(id);
        return ResultHelper.ok();
    }
    @GetMapping("/filterByDrDate/{doctorId}")
    public ResultData<List<AppointmentResponse>> getDoctorIdAndDate(
            @PathVariable("doctorId") int id,
            @RequestParam(name = "entryDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate entryDate,
            @RequestParam(name = "exitDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate exitDate,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ){
        return this.appointmentService.findByDoctorIdAndDateTimeBetween(id,entryDate,exitDate, page, pageSize);
    }
    @GetMapping("/filterByDrNameDate/{name}")
    public ResultData<List<AppointmentResponse>> getDoctorNameAndDate(
            @PathVariable("name") String name,
            @RequestParam(name = "entryDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate entryDate,
            @RequestParam(name = "exitDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate exitDate,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ){
        return this.appointmentService.findByDoctorNameContainingAndDateTimeBetween(name,entryDate,exitDate, page, pageSize);
    }

    @GetMapping("/filterByAnmlNameDate/{name}")
    public ResultData<List<AppointmentResponse>> getAnimalNameAndDate(
            @PathVariable("name") String name,
            @RequestParam(name = "entryDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate entryDate,
            @RequestParam(name = "exitDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate exitDate,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ){
        return this.appointmentService.findByAnimalNameContainingAndDateTimeBetween(name,entryDate,exitDate, page, pageSize);
    }

    @GetMapping("/filterByAnmlDate/{animalId}")
    public ResultData<List<AppointmentResponse>> getAnimalIdAndDate(
            @PathVariable("animalId") int id,
            @RequestParam(name = "entryDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate entryDate,
            @RequestParam(name = "exitDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate exitDate,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ){
        return this.appointmentService.findByAnimalIdAndDateTimeBetween(id, entryDate, exitDate, page, pageSize);
    }

     @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> getById(@PathVariable int id){
        return this.appointmentService.getById(id);
     }
}
