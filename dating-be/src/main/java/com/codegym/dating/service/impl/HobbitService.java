package com.codegym.dating.service.impl;

import com.codegym.dating.model.Hobbit;
import com.codegym.dating.repository.IHobbitRepository;
import com.codegym.dating.service.IHobbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HobbitService implements IHobbitService {

    @Autowired
    private IHobbitRepository iHobbitRepository;

    @Override
    public List<Hobbit> findAllHobbit() {
        return this.iHobbitRepository.findAllHobbit();
    }

    @Override
    public Hobbit findById(int id) {
        Hobbit hobbit = this.iHobbitRepository.findById(id).get();
        return hobbit;
    }
}