package com.codegym.dating.service;

import com.codegym.dating.model.UserTarget;
import org.springframework.data.jpa.repository.Query;

public interface IUserTargetService {

    @Query(value = "insert into user_target (id_user, id_target)" +
            " value (:#{#userTarget.user.idUser}, :#{#userTarget.target.idTarget}) ", nativeQuery = true)
    void saveUserTarget(UserTarget userTarget);
}