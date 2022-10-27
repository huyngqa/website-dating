package com.codegym.dating.service;

import com.codegym.dating.dto.IReportDetailsDto;
import com.codegym.dating.dto.ReportDetailDto;
import com.codegym.dating.dto.ReportDetailsDto;
import com.codegym.dating.dto.UserReportDto;
import com.codegym.dating.model.ReportDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IReportDetailsService {
    List<ReportDetailDto> findByIdReportDetailUser(int id);

    List<UserReportDto> findByUserList(List<Integer> userIds);

    Page<IReportDetailsDto> findAll(String keyWord, Pageable pageable);

    void confirm(int id);

    void save(ReportDetailsDto reportDetailsDto);

    void delete(int id);

    ReportDetails findById (Integer id);
}
