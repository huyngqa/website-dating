package com.codegym.dating.service.impl;

import com.codegym.dating.model.Target;
import com.codegym.dating.repository.ITargetRepository;
import com.codegym.dating.service.ITargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TargetService implements ITargetService {

    @Autowired
    private ITargetRepository iTargetRepository;

    @Override
    public List<Target> findAllTarget() {
        return this.iTargetRepository.findAllTarget();
    }

    @Override
    public Target findById(int id) {
        Target target = this.iTargetRepository.findById(id).get();
        return target;
    }
}