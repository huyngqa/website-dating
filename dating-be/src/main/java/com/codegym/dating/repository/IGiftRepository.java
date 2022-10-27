package com.codegym.dating.repository;

import com.codegym.dating.model.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IGiftRepository extends JpaRepository<Gift, Integer> {
    @Query(value = "select * from gift", nativeQuery = true)
    List<Gift> findAllGift();

    @Query(value = "select * from gift where id_gift = :idGift", nativeQuery = true )
    Optional<Gift> findById(@Param("idGift") Integer idGift);
}