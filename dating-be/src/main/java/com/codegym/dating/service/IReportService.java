package com.codegym.dating.service;

import com.codegym.dating.dto.ReportDto;

import java.util.List;

public interface IReportService {
    List<ReportDto> findAllReport();

    ReportDto findReportById(Integer id);
}
