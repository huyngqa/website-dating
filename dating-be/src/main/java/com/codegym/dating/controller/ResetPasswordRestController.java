package com.codegym.dating.controller;

import com.codegym.dating.model.Account;
import com.codegym.dating.model.JwtRequest;
import com.codegym.dating.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api")
public class ResetPasswordRestController {

    @Autowired
    private IAccountService iAccountService;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostMapping("/users/account/changePassword/{idAccount}")
    public ResponseEntity<Void> doResetPassword(@RequestBody JwtRequest authenticationRequest,
                                                @PathVariable Integer idAccount) {
        Optional<Account> account = iAccountService.findById(idAccount);

        if (account.isPresent() && BCrypt.checkpw(authenticationRequest.getPassword(), account.get().getPassword()) &&
                !Objects.equals(authenticationRequest.getNewPassword(), "")) {
            iAccountService.saveNewPassword(passwordEncoder().encode(authenticationRequest.getNewPassword()), idAccount);
            return new ResponseEntity<>(HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}