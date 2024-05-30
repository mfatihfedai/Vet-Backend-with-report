package com.project.vetProject.business.concretes;

import com.project.vetProject.business.abstracts.IReportService;
import com.project.vetProject.core.config.modelMapper.IModelMapperService;
import com.project.vetProject.core.exception.NotFoundException;
import com.project.vetProject.core.result.ResultData;
import com.project.vetProject.core.utilies.Msg;
import com.project.vetProject.core.utilies.ResultHelper;
import com.project.vetProject.dao.ReportRepo;
import com.project.vetProject.dto.CursorResponse;
import com.project.vetProject.dto.request.report.ReportSaveRequest;
import com.project.vetProject.dto.request.report.ReportUpdateRequest;
import com.project.vetProject.dto.response.report.ReportResponse;
import com.project.vetProject.entity.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportManager implements IReportService {
    public final ReportRepo reportRepo;
    public final IModelMapperService modelMapperService;
    @Override
    public ResultData<ReportResponse> save(ReportSaveRequest reportSaveRequest) {
        Report saveReport = this.modelMapperService.forRequest().map(reportSaveRequest, Report.class);
        return ResultHelper.created(this.modelMapperService.forResponse().map(this.reportRepo.save(saveReport), ReportResponse.class));
    }

    @Override
    public Report get(int id) {
        return reportRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public ResultData<CursorResponse<ReportResponse>> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Report> reportPage = this.reportRepo.findAll(pageable);
        Page<ReportResponse> reportResponsePage = reportPage.map(report -> this.modelMapperService.forResponse().map(report, ReportResponse.class));
        return ResultHelper.cursor(reportResponsePage);
    }

    @Override
    public ResultData<ReportResponse> update(ReportUpdateRequest reportUpdateRequest) {
        this.get(reportUpdateRequest.getId());
        Report updateReport = this.modelMapperService.forRequest().map(reportUpdateRequest, Report.class);
        return ResultHelper.created(this.modelMapperService.forResponse().map(updateReport, ReportResponse.class));
    }

    @Override
    public boolean delete(int id) {
        Report report = this.get(id);
        this.reportRepo.delete(report);
        return true;
    }

    @Override
    public ResultData<ReportResponse> getById(int id) {
        Report report = this.get(id);
        Report updateReport = this.modelMapperService.forRequest().map(report, Report.class);
        return ResultHelper.success(this.modelMapperService.forResponse().map(updateReport, ReportResponse.class));
    }
}
