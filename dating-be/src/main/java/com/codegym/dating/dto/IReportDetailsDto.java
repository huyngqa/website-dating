package com.codegym.dating.dto;

import java.time.LocalDateTime;

public interface IReportDetailsDto {
    Integer getId();
    Integer getPost();
    Integer getReporter();
    String getUserReport();
    Integer getReport();
    Integer getStatus();
    String getUserPost();
    String getPostContent();
    String getReportContent();
    LocalDateTime getTimeReport();
}