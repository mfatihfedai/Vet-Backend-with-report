package com.project.vetProject.business.abstracts;

import com.project.vetProject.core.result.ResultData;
import com.project.vetProject.dto.CursorResponse;
import com.project.vetProject.dto.request.report.ReportSaveRequest;
import com.project.vetProject.dto.request.report.ReportUpdateRequest;
import com.project.vetProject.dto.response.report.ReportResponse;
import com.project.vetProject.entity.Report;

public interface IReportService {
    ResultData<ReportResponse> save(ReportSaveRequest reportSaveRequest);
    Report get(int id);
    ResultData<CursorResponse<ReportResponse>> cursor(int page, int pageSize);
    ResultData<ReportResponse> update(ReportUpdateRequest reportUpdateRequest);
    boolean delete(int id);
    ResultData<ReportResponse> getById(int id);
}
