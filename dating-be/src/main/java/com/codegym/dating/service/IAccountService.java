package com.codegym.dating.service;

import com.codegym.dating.common.AuthenticationProvider;
import com.codegym.dating.model.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
//    void saveAccount(Account account);

    Account getAccountByEmail(String email);

    void createAccountForFacebook(String email, AuthenticationProvider authenticationProvider);

    void updateAccountIfExists(Account account, AuthenticationProvider authenticationProvider);
    Account findAccountByEmail(String email);

    List<Account> findAllAccount();

    Account findAccountById(Integer id);

    Account saveAccount(Account account);

    void updateAccount(Account account);
    Optional<Account> findById(Integer idAccount);

    void saveNewPassword(String password, Integer idAccount);
}
