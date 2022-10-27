package com.codegym.dating.service.impl;

import com.codegym.dating.model.StatusActive;
import com.codegym.dating.repository.IStatusActiveRepository;
import com.codegym.dating.service.IStatusActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusActiveService implements IStatusActiveService {
    @Autowired
    private IStatusActiveRepository iStatusActiveRepository;
    @Override
    public StatusActive getStatusById(Integer id) {
        return iStatusActiveRepository.findById(id).orElse(null);
    }
}
