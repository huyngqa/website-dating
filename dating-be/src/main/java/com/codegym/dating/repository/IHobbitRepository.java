package com.codegym.dating.repository;

import com.codegym.dating.model.Hobbit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IHobbitRepository extends JpaRepository<Hobbit, Integer> {

    @Query(value = "select * from hobbit", nativeQuery = true)
    List<Hobbit> findAllHobbit();
}