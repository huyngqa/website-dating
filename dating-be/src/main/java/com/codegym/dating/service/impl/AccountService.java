package com.codegym.dating.service.impl;

import com.codegym.dating.common.AuthenticationProvider;
import com.codegym.dating.model.Account;
import com.codegym.dating.model.AccountRole;
import com.codegym.dating.model.Role;
import com.codegym.dating.model.composite.AccountRoleKey;
import com.codegym.dating.repository.IAccountRepository;
import com.codegym.dating.repository.IAccountRoleRepository;
import com.codegym.dating.repository.IRoleRepository;
import com.codegym.dating.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository iAccountRepository;

    @Autowired
    private IRoleRepository iRoleRepository;

    @Autowired
    private IAccountRoleRepository iAccountRoleRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public Account findAccountByEmail(String email) {
        return iAccountRepository.findByEmail(email);
    }

    @Override
    public List<Account> findAllAccount() {
        return this.iAccountRepository.findAllAccount();
    }

    @Override
    public Account findAccountById(Integer id) {
        return this.iAccountRepository.findAccountById(id);
    }

    @Override
    public Account saveAccount(Account account) {

        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));

        AccountRole accountRole = new AccountRole();

        Role role = iRoleRepository.findById(2).orElse(null);

        account.setStatus(0);

        this.iAccountRepository.save(account);

        accountRole.setRole(role);

        accountRole.setAccount(account);

        AccountRoleKey accountRoleKey = new AccountRoleKey(account.getIdAccount(), role.getIdRole());

        accountRole.setId(accountRoleKey);

        iAccountRoleRepository.save(accountRole);

        return account;
    }

    @Override
    public void updateAccount(Account account) {
        this.iAccountRepository.updateAccount(account);
    }

    @Override
    public Account getAccountByEmail(String email) {
        return iAccountRepository.findByEmail(email);
    }

    @Override
    public void createAccountForFacebook(String email, AuthenticationProvider authenticationProvider) {
        Account account = new Account();
        account.setEmail(email);
        account.setAuthProvider(authenticationProvider);
        iAccountRepository.save(account);
    }

    @Override
    public void updateAccountIfExists(Account account, AuthenticationProvider authenticationProvider) {
        account.setAuthProvider(authenticationProvider);
        iAccountRepository.save(account);
    }

    @Override
    public Optional<Account> findById(Integer idAccount) {
        return iAccountRepository.findById(idAccount);
    }

    @Override
    public void saveNewPassword(String password, Integer idAccount) {
        iAccountRepository.saveNewPassword(password, idAccount);
    }


}
