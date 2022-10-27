package com.codegym.dating.ulti;

import com.codegym.dating.dto.UserClassDto;
import com.codegym.dating.dto.UserDto;
import org.springframework.validation.Errors;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

public class UserValidate {

    static public void checkName(UserDto userDto, Errors errors) {

        if (userDto.getName().isEmpty()){
            errors.rejectValue("name",
                    "create.name",
                    "Vui lòng nhập !");
        }
    }

    static public void checkGender(UserDto userDto, Errors errors) {

        if (userDto.getGender() == null){
            errors.rejectValue("gender",
                    "create.gender",
                    "Vui lòng chọn giới tính nhập !");
        }
    }

    static public void checkAge(UserClassDto userClassDto, Errors errors) {

        if (!userClassDto.getDateOfBirth().isEmpty()){

            try {

                LocalDate dateOfBirth = LocalDate.parse(userClassDto.getDateOfBirth());

                int age = Period.between(dateOfBirth, LocalDate.now()).getYears();

                if (age < 18){
                    errors.rejectValue("dateOfBirth",
                            "create.dateOfBirth",
                            "Bạn chưa đủ 18 tuổi !");
                }
            }catch (DateTimeParseException e){
                errors.rejectValue("dateOfBirth",
                        "create.dateOfBirth",
                        "Vui lòng nhập đúng định dạng !");
            }
        }else {
            errors.rejectValue("dateOfBirth",
                    "create.dateOfBirth",
                    "Vui lòng chọn ngày sinh !");
        }
    }

    static public void checkJob(UserDto userDto, Errors errors) {

        if (userDto.getJob().isEmpty()) {
            errors.rejectValue("job",
                    "create.job",
                    "Vui lòng nhập công việc!");
        }
    }

    static public void checkAddress(UserDto userDto, Errors errors) {

        if (userDto.getAddress().isEmpty()) {
            errors.rejectValue("address",
                    "create.address",
                    "Vui lòng nhập địa chỉ!");
        }
    }

    static public void checkMarried(UserDto userDto, Errors errors) {

        if (userDto.getMarried() == null) {
            errors.rejectValue("married",
                    "create.married",
                    "Vui lòng chọn tình trạng hôn nhân!");
        }
    }


    static public void checkHobbit(UserClassDto userClassDto, Errors errors) {

        if (userClassDto.getUserHobbits().isEmpty()) {
            errors.rejectValue("userHobbits",
                    "create.userHobbits",
                    "Vui lòng chọn một vài sở thích!");
        }
    }

    static public void checkTarget(UserClassDto userClassDto, Errors errors) {

        if (userClassDto.getUserTargets().isEmpty()) {
            errors.rejectValue("userTargets",
                    "create.userTargets",
                    "Vui lòng chọn mục đích tham gia !");
        }
    }

}