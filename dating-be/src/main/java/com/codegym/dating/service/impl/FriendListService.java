package com.codegym.dating.service.impl;

import com.codegym.dating.dto.RelationshipDto;
import com.codegym.dating.dto.UserDto;
import com.codegym.dating.projection_dto.IUserDto;
import com.codegym.dating.repository.IFriendListRepository;
import com.codegym.dating.service.IFriendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendListService implements IFriendListService {
    @Autowired
    private IFriendListRepository iFriendListRepository;

    @Override
    public List<UserDto> findAllRequest(int id) {
        return iFriendListRepository.findAllRequestFriend(id);
    }

    @Override
    public int checkFriend(Integer idUser1, Integer idUser2) {
        RelationshipDto relationshipDto = this.iFriendListRepository.checkFriend(idUser1, idUser2);
        if (relationshipDto == null) {
            return 0;
        } else if (relationshipDto.getStatus() == 6) {
            return 6;
        } else if (relationshipDto.getStatus() == 7) {
            return 7;
        } else if (relationshipDto.getStatus() == 5 && relationshipDto.getIdUser1() == idUser1) {
            return 51;
        } else {
            return 52;
        }
    }


    @Override
    public String checkFriend1(Integer idUser1, Integer idUser2) {
        RelationshipDto relationshipDto = this.iFriendListRepository.checkFriend(idUser1, idUser2);
        if (relationshipDto == null) {
            return "Chưa có quan hệ";
        } else if (relationshipDto.getStatus() == 6) {
            return "Bạn bè";
        } else if (relationshipDto.getStatus() == 7) {
            return "Bị chặn";
        } else if (relationshipDto.getStatus() == 5 && relationshipDto.getIdUser1() == idUser1) {
            return "Đã gửi lời mời kết bạn";
        } else {
            return "Đã nhận lời mời";
        }
    }
        @Override
        public Page<IUserDto> findAllFriendList (Integer id, String name, Pageable pageable){
            return iFriendListRepository.findAllListFriend(id, "%" + name + "%", pageable);
        }

//    @Override
//    public void blockFriend(Integer[] idUser) {
//        for (int i = 0; i < idUser.length; i++) {
//            iFriendListRepository.blockFriend(idUser[i], idUser[i + 1]);
//        }
//    }

        @Override
        public void deleteFriend (Integer idUser, Integer[]friendList){
            for (int i = 0; i < friendList.length; i++) {
                this.iFriendListRepository.deleteFriend(idUser, friendList[i]);
            }
        }

        @Override
        public void addRequest (Integer idUser1, Integer idUser2){
            this.iFriendListRepository.addRequest(idUser1, idUser2);
        }

        @Override
        public void removeRequest (Integer idUser1, Integer idUser2){
            this.iFriendListRepository.removeRequest(idUser1, idUser2);
        }


    @Override
        public void acceptRequest (Integer idUser1, Integer idUser2){
            iFriendListRepository.acceptRequest(idUser1, idUser2);
        }

        @Override
        public void deniedRequest (Integer idUser1, Integer idUser2){
            iFriendListRepository.deniedRequest(idUser1, idUser2);
        }
        public void blockFriend (Integer idUser, Integer[]friendList){
            for (int i = 0; i < friendList.length; i++) {
                this.iFriendListRepository.blockFriend(idUser, friendList[i]);
            }
        }

    @Override
    public List<UserDto> suggestRequest(Integer userId, boolean gender) {
        return this.iFriendListRepository.suggestRequest(userId,gender);
    }

//    @Override
//    public Page<IUserDto> findAllListFriend(Integer idUser, String name, Pageable pageable) {
//        return this.iFriendListRepository.findAllListFriend(idUser,name,pageable);
//    }

//    @Override
//    public void deleteFriend(int idUser1, int idUser2) {
//        this.iFriendListRepository.deleteFriend(idUser1,idUser2);
//    }

//    @Override
//    public void deleteFriend(Integer[] idUser2) {
//        for (int i = 0; i < idUser2.length; i++) {
//            iFriendListRepository.deleteFriend(idUser2[i], idUser2[i + 1]);
//        }
//    }
    }
