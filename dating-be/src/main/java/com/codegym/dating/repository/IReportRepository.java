package com.codegym.dating.repository;

import com.codegym.dating.dto.ReportDto;
import com.codegym.dating.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface IReportRepository extends JpaRepository<Report, Integer> {
    @Transactional
    @Query(value = "select report.id_report as idReport, report.name_report as nameReport from report", nativeQuery = true)
    List<ReportDto> findAllReport();

    @Transactional
    @Query(value = "select report.id_report, report.name_report from report where id_report = :id", nativeQuery = true)
    ReportDto findReportByID(@Param("id") Integer id);
}
