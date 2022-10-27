package com.codegym.dating.service;

import com.codegym.dating.dto.UserDto;
import com.codegym.dating.projection_dto.IUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFriendListService {
    List<UserDto> findAllRequest(int id);
    void acceptRequest(Integer idUser1 , Integer idUser2);
    void deniedRequest(Integer idUser1 , Integer idUser2);


    String checkFriend1(Integer idUser1, Integer idUser2);
    int checkFriend(Integer idUser1, Integer idUser2);
    void addRequest(Integer idUser1, Integer idUser2);
    void removeRequest(Integer idUser1, Integer idUser2);

    Page<IUserDto> findAllFriendList(Integer id, String name, Pageable pageable);

    void deleteFriend(Integer idUser, Integer[] friendList);

    void blockFriend(Integer idUser, Integer[] friendList);
    List<UserDto> suggestRequest(Integer userId, boolean gender);

}
