package com.codegym.dating.repository;

import com.codegym.dating.model.TypeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ITypeUserRepository extends JpaRepository<TypeUser, Integer> {

    @Query(value = "select * from type_user where id_type_user = :id", nativeQuery = true)
    TypeUser findTypeUserById(Integer id);
}