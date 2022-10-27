package com.codegym.dating.dto;

import java.time.LocalDate;

public interface UserDto {
    Integer getIdUser();

    String getAddress();

    LocalDate getDateOfBirth();

    String getName();

    String getJob();

    String getAvatar();

    Integer getCoin();

    Boolean getGender();

    Boolean getMarried();

    Integer statusActive();

    Integer getIdTypeUser();

    LocalDate getJoinDay();

    String getTypeUserName();

    Boolean getIsMarried();

    Boolean getIsGender();

    String getHobbitName();
}