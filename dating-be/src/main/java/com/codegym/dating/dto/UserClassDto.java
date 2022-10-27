package com.codegym.dating.dto;

import com.codegym.dating.model.*;
import com.codegym.dating.ulti.UserValidate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserClassDto implements Validator {

    private Integer idUser;

    @NotBlank(message = "Vui lòng nhập tên !")
    private String name;

    private String dateOfBirth;

    @NotNull(message = "Vui lòng chọn giới tính !")
    private Boolean gender;

    @NotBlank(message = "Vui lòng nhập địa chỉ !")
    private String address;

    @NotBlank(message = "Vui lòng nhập công việc !")
    private String job;

    @NotNull(message = "Vui lòng chọn tình trạng hôn nhân !")
    private Boolean married;

    private String avatar;

    private List<Integer> hobbits;

    private List<Integer> targets;

    private String joinDay;

    private Integer coin;

    private Integer statusActive;

    private AccountDto accountDto;

    private TypeUser typeUser;

    private List<UserHobbit> userHobbits;

    private List<Post> postList;

    private List<Comment> comments;

    private List<Invoice> invoices;

    private List<UserTarget> userTargets;

    private List<GiftUser> giftSenders;

    private List<GiftUser> giftReceiver;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserClassDto userClassDto = (UserClassDto) target;

        UserValidate.checkAge(userClassDto, errors);
    }
}
