package com.codegym.dating.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDetailDto {
    private int idUser;
    private String name;
    private LocalDateTime timeReport;
    private String nameReport;
    private String username;
}
