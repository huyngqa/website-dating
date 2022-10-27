package com.codegym.dating.service;

import com.codegym.dating.dto.HobbitDto;
import com.codegym.dating.model.UserHobbit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserHobbitService {
    List<HobbitDto> findAllByIdUser(Integer id);
    void saveUserHobbit(UserHobbit userHobbit);

}
