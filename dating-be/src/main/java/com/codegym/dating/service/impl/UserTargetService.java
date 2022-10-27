package com.codegym.dating.service.impl;

import com.codegym.dating.model.UserTarget;
import com.codegym.dating.repository.IUserTargetRepository;
import com.codegym.dating.service.IUserTargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTargetService implements IUserTargetService {

    @Autowired
    private IUserTargetRepository iUserTargetRepository;

    @Override
    public void saveUserTarget(UserTarget userTarget) {
        this.iUserTargetRepository.saveUserTarget(userTarget);
    }
}
