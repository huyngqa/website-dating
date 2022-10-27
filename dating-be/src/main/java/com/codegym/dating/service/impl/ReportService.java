package com.codegym.dating.service.impl;

import com.codegym.dating.dto.ReportDto;
import com.codegym.dating.repository.IReportRepository;
import com.codegym.dating.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService implements IReportService {
    @Autowired
    IReportRepository reportRepository;

    @Override
    public List<ReportDto> findAllReport() {
        return reportRepository.findAllReport();
    }

    @Override
    public ReportDto findReportById(Integer id) {
        return reportRepository.findReportByID(id);
    }

}
