package com.codegym.dating.repository;

import com.codegym.dating.model.AccountRole;
import com.codegym.dating.model.composite.AccountRoleKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRoleRepository extends JpaRepository<AccountRole, AccountRoleKey> {
}
