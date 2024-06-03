package com.project.vetProject.api;

import com.project.vetProject.business.abstracts.IAppointmentService;
import com.project.vetProject.business.abstracts.IReportService;
import com.project.vetProject.core.result.Result;
import com.project.vetProject.core.result.ResultData;
import com.project.vetProject.core.utilies.ResultHelper;
import com.project.vetProject.dto.CursorResponse;
import com.project.vetProject.dto.request.report.ReportSaveRequest;
import com.project.vetProject.dto.request.report.ReportUpdateRequest;
import com.project.vetProject.dto.response.report.ReportResponse;
import com.project.vetProject.entity.Appointment;
import com.project.vetProject.entity.Report;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/reports")
@RequiredArgsConstructor
public class ReportController {
    private final IReportService reportService;
    private final IAppointmentService appointmentService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<ReportResponse> save(@Valid @RequestBody ReportSaveRequest reportSaveRequest){
        return reportService.save(reportSaveRequest);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<ReportResponse> update(@Valid @RequestBody ReportUpdateRequest reportUpdateRequest){
        return reportService.update(reportUpdateRequest);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<ReportResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return reportService.cursor(page, pageSize);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.reportService.delete(id);
        return ResultHelper.ok();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<ReportResponse> get(@PathVariable("id") int id){
        return this.reportService.getById(id);
    }
    @GetMapping("/appointment/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Report> getAppointment(@PathVariable("id") int id){
        Appointment appointment = this.appointmentService.get(id);
        return this.reportService.findByAppointment(appointment);
    }
}
