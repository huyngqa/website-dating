package com.codegym.dating.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Vui lòng nhập tên đăng nhập")
    @Size(min = 12, max = 40)
    private String username;
    @NotBlank(message = "Vui lòng nhập mật khẩu")
    @Size(min = 6, max = 40)
    private String password;
}
