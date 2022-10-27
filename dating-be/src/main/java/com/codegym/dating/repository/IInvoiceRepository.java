package com.codegym.dating.repository;

import com.codegym.dating.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
@Transactional
public interface IInvoiceRepository extends JpaRepository<Invoice, Integer> {
    @Modifying
    @Query(value = "INSERT INTO invoice (`price`, `time`, `id_user`) VALUES (:price, :time, :id_user);",
            nativeQuery = true)
    void savePaypal(@Param("price") Integer price, @Param("time") LocalDate time, @Param("id_user") Integer idUser);
}
