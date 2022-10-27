package com.codegym.dating.service.impl;

import com.codegym.dating.dto.HobbitDto;
import com.codegym.dating.model.UserHobbit;
import com.codegym.dating.repository.IUserHobbitRepository;
import com.codegym.dating.service.IUserHobbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserHobbitService implements IUserHobbitService {
    @Autowired
    private IUserHobbitRepository iUserHobbitRepository;
    @Override
    public List<HobbitDto> findAllByIdUser(Integer id) {
        return this.iUserHobbitRepository.findAllByIdUser(id);
    }
    @Override
    public void saveUserHobbit(UserHobbit userHobbit) {
        this.iUserHobbitRepository.saveUserHobbit(userHobbit);
    }
}
