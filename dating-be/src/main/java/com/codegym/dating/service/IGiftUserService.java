package com.codegym.dating.service;

import com.codegym.dating.model.GiftUser;

import java.util.List;

public interface IGiftUserService {
    List<GiftUser> findAllGiftUser();
    void updateGiveAGift(Integer idGift , Integer idUserSender , Integer idUserReceiver, Integer quantity);
}