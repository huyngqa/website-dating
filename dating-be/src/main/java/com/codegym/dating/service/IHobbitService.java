package com.codegym.dating.service;

import com.codegym.dating.model.Hobbit;

import java.util.List;

public interface IHobbitService {

    List<Hobbit> findAllHobbit();

    Hobbit findById(int id);
}
