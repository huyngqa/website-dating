package com.codegym.dating.service.impl;

import com.codegym.dating.model.GiftUser;
import com.codegym.dating.repository.IGiftUserRepository;
import com.codegym.dating.service.IGiftUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GiftUserService implements IGiftUserService {
    @Autowired
    private IGiftUserRepository giftUserRepository;

    @Override
    public List<GiftUser> findAllGiftUser() {
        return this.giftUserRepository.findAllGiftUser();
    }

    @Transactional
    @Override
    public void updateGiveAGift(Integer idGift, Integer idUserSender, Integer idUserReceiver, Integer quantity) {
        List<GiftUser> giftUsers = findAllGiftUser();
        boolean flag = true;
        for (GiftUser item : giftUsers) {
            if (item.getGift().getIdGift().equals(idGift) && item.getSender().getIdUser().equals(idUserSender) && item.getReceiver().getIdUser().equals(idUserReceiver)) {
                this.giftUserRepository.updateGiveAGift(idGift, idUserSender, idUserReceiver, quantity);
                flag = false;
            }
        }
        if (flag) {
            this.giftUserRepository.saveGiveAGift(idGift, idUserSender, idUserReceiver, quantity);
        }
    }
}