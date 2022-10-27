package com.codegym.dating.service;

import com.codegym.dating.model.Gift;

import java.util.List;
import java.util.Optional;

public interface IGiftService {
    List<Gift> findAllGift();

    Optional<Gift> findById(Integer idGift);
}
