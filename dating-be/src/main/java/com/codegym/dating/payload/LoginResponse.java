package com.codegym.dating.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Integer idAccount;
    private String email;
    private List<String> roles;

    public LoginResponse(String accessToken, Integer idAccount, String email, List<String> roles) {
        this.accessToken = accessToken;
        this.idAccount = idAccount;
        this.email = email;
        this.roles = roles;
    }
}
