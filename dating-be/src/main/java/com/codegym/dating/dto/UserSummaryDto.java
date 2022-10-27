package com.codegym.dating.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSummaryDto {
    private Integer idUser;
    private String name;
    private Integer coin;
    private LocalDate joinDay;
    private String typeUser;
    private long quantity;
}
