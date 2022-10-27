package com.codegym.dating.oauth2;

import com.codegym.dating.common.AuthenticationProvider;
import com.codegym.dating.model.Account;
import com.codegym.dating.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private IAccountService iAccountService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2Account oAuth2Account = (OAuth2Account) authentication.getPrincipal();
        String email = oAuth2Account.getEmail();
        Account account = iAccountService.getAccountByEmail(email);
        if (account == null) {
            iAccountService.createAccountForFacebook(email, AuthenticationProvider.FACEBOOK);
        } else {
            iAccountService.updateAccountIfExists(account, AuthenticationProvider.FACEBOOK);
        }
        System.out.println("Đã đăng nhập với email: " + email);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
