package com.codegym.dating.service;

import com.codegym.dating.model.Target;

import java.util.List;

public interface ITargetService {

    List<Target> findAllTarget();

    Target findById(int id);
}
