package com.codegym.dating.repository;

import com.codegym.dating.model.UserTarget;
import com.codegym.dating.model.composite.UserTargetKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface IUserTargetRepository extends JpaRepository<UserTarget, UserTargetKey> {

    @Transactional
    @Modifying
    @Query(value = "insert into user_target (id_target, id_user) " +
            "value (:#{#userTarget.target.idTarget}, :#{#userTarget.user.idUser})", nativeQuery = true)
    void saveUserTarget(UserTarget userTarget);
}