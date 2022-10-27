package com.codegym.dating.dto;

import com.codegym.dating.model.AccountRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto implements Validator {

    private Integer idAccount;

    @NotBlank(message = "vui lòng nhập email !")
    @Email(message = "vui lòng nhập đúng định dạng !")
    private String email;

    @NotBlank(message = "vui lòng nhập số điện thoại !")
    @Pattern(regexp = "^(090|093|097)\\d{7}$", message = "vui lòng nhập đúng định dạng !")
    private String phone;

    @NotBlank(message = "vui lòng nhập mật khẩu!")
    @Length(min = 6, message = "Mật khẩu yếu !")
    @Length(max = 50, message = "Tối đa 50 ký tự")
    private String password;

    private Integer status;

    private UserDto userDto;

    private Set<AccountRole> accountRoles;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
    }
}