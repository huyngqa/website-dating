package com.codegym.dating.repository;

import com.codegym.dating.model.GiftUser;
import com.codegym.dating.model.composite.GiftUserKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IGiftUserRepository extends JpaRepository<GiftUser, GiftUserKey> {

    @Query(value = "select * from gift_user", nativeQuery = true)
    List<GiftUser> findAllGiftUser();

    @Modifying
    @Transactional
    @Query(value = "CALL `dating_c04`.update_give_a_gift(:idGift, :idUserSender, :idUserReceiver, :quantity)", nativeQuery = true)
    void updateGiveAGift(@Param("idGift") Integer idGift, @Param("idUserSender") Integer idUserSender, @Param("idUserReceiver") Integer idUserReceiver, @Param("quantity") Integer quantity);

    @Modifying
    @Transactional
    @Query(value = "CALL `dating_c04`.create_give_a_gift(:idGift, :idUserSender, :idUserReceiver, :quantity);", nativeQuery = true)
    void saveGiveAGift(@Param("idGift") Integer idGift, @Param("idUserSender") Integer idUserSender, @Param("idUserReceiver") Integer idUserReceiver, @Param("quantity") Integer quantity);


}