package com.codegym.dating.dto;


import java.time.LocalDateTime;

public class ReportDetailsDto {
    private Integer id;
    private Integer post;
    private Integer reporter;
    private Integer report;
    private Integer status;
    private LocalDateTime timeReport;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public Integer getReporter() {
        return reporter;
    }

    public void setReporter(Integer reporter) {
        this.reporter = reporter;
    }

    public Integer getReport() {
        return report;
    }

    public void setReport(Integer report) {
        this.report = report;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getTimeReport() {
        return timeReport;
    }

    public void setTimeReport(LocalDateTime timeReport) {
        this.timeReport = timeReport;
    }
}