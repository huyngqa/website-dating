package com.codegym.dating.repository;

import com.codegym.dating.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IAccountRepository extends JpaRepository<Account, Integer> {

    Account findByEmail(String email);
    @Query(value = "select * from account", nativeQuery = true)
    List<Account> findAllAccount();

    @Query(value = "select * from account where id_account = :id", nativeQuery = true)
    Account findAccountById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "insert into account (email, password, phone, status)" +
            "value (:#{#account.email}, :#{#account.password}, :#{#account.phone}, :#{#account.status})", nativeQuery = true)
    void saveAccount(Account account);

    @Modifying
    @Transactional
    @Query(value = "update account set id_user = :#{#account.user.idUser}, status = 1 " +
            "where id_account = :#{#account.idAccount}", nativeQuery = true)
    void updateAccount(Account account);

    @Query(value = "SELECT * FROM account where id_account =:idAccount", nativeQuery = true)
    Optional<Account> findById(@Param("idAccount") Integer idAccount);

    @Modifying
    @Transactional
    @Query(value = "update account set password =?1 where id_account = ?2", nativeQuery = true)
    void saveNewPassword(String password, Integer idAccount);
    @Query(value = "select * from account where id_user = :id", nativeQuery = true)
    Account findAccountByIdUser(Integer id);
}
