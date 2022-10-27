package com.codegym.dating.service.impl;

import com.codegym.dating.dto.ReportDetailDto;
import com.codegym.dating.dto.UserReportDto;
import com.codegym.dating.repository.IReportDetailsRepository;
import com.codegym.dating.service.IReportDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.codegym.dating.dto.IReportDetailsDto;
import com.codegym.dating.dto.ReportDetailsDto;
import com.codegym.dating.model.ReportDetails;


import java.util.List;

@Service
public class ReportDetailService implements IReportDetailsService {

    @Autowired
    private IReportDetailsRepository iReportDetailsRepository;

    @Override
    public List<ReportDetailDto> findByIdReportDetailUser(int id) {
        return iReportDetailsRepository.findByIdReportDetail(id);
    }

    @Override
    public List<UserReportDto> findByUserList(List<Integer> userIds) {
        return iReportDetailsRepository.findByUserList(userIds);
    }

    @Override
    public Page<IReportDetailsDto> findAll(String keyWord, Pageable pageable) {
        return iReportDetailsRepository.findByAllReportUser(keyWord, pageable);
    }

    @Override
    public void confirm(int id) {
        ReportDetails reportDetails = findById(id);
        reportDetails.setStatus(9);
        iReportDetailsRepository.save(reportDetails);
    }

    @Override
    public void save(ReportDetailsDto reportDetailsDto) {
        iReportDetailsRepository.createReportDetails(reportDetailsDto.getId(), reportDetailsDto.getReport(),
                reportDetailsDto.getPost(),reportDetailsDto.getReporter(),reportDetailsDto.getTimeReport(),reportDetailsDto.getStatus());
    }

    @Override
    public void delete(int id) {
        ReportDetails reportDetails = findById(id);
        reportDetails.setStatus(13);
        iReportDetailsRepository.save(reportDetails);
    }

    @Override
    public ReportDetails findById(Integer id) {
        return iReportDetailsRepository.findReportDetailsByID(id);
    }
}
