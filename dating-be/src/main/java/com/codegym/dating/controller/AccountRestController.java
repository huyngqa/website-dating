package com.codegym.dating.controller;

import com.codegym.dating.dto.AccountDto;
import com.codegym.dating.model.Account;
import com.codegym.dating.model.User;
import com.codegym.dating.service.IAccountService;
import com.codegym.dating.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("api/")
public class AccountRestController {

    @Autowired
    private IAccountService iAccountService;

    @Autowired
    private IUserService iUserService;


    @PostMapping("public/account/save")
    public ResponseEntity<Map<String, String>> saveAccount(@RequestBody @Valid AccountDto accountDto,
                                                           BindingResult bindingResult) {

        new AccountDto().validate(accountDto, bindingResult);

        if (bindingResult.hasErrors()) {
            Map<String, String> errMap = new HashMap<>();

            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errMap, HttpStatus.BAD_REQUEST);
        }

        Account account = new Account();
        BeanUtils.copyProperties(accountDto, account);

        User user = new User();

        this.iUserService.saveUser(user);

        account.setUser(user);

        this.iAccountService.saveAccount(account);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("users/my-account/{email}")
    public ResponseEntity<Account> getAccount(@PathVariable String email) {
        Account account = iAccountService.getAccountByEmail(email);
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}