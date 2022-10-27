package com.codegym.dating.service.impl;

import com.codegym.dating.model.TypeUser;
import com.codegym.dating.repository.ITypeUserRepository;
import com.codegym.dating.service.ITypeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeUserService implements ITypeUserService {
    @Autowired
    private ITypeUserRepository iTypeUserRepository;

    @Override
    public List<TypeUser> findAllTypeUser() {
        return iTypeUserRepository.findAll();
    }
}
