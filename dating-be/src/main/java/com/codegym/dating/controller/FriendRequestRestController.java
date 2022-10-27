package com.codegym.dating.controller;

import com.codegym.dating.service.IFriendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("api")
public class FriendRequestRestController {
    @Autowired
    private IFriendListService iFriendListService;

    @GetMapping("/users/friendList/check/{idUser1}/{idUser2}")
    public ResponseEntity<Integer>checkFriend(@PathVariable int idUser1, @PathVariable int idUser2){
        int relationship = this.iFriendListService.checkFriend(idUser1,idUser2);
        return new ResponseEntity<>(relationship, HttpStatus.OK);
    }

    @PostMapping("/users/friendList/addRequest/{idUser1}/{idUser2}")
    public ResponseEntity<Void>addRequest(@PathVariable int idUser1, @PathVariable int idUser2){
        int isFriend = this.iFriendListService.checkFriend(idUser1,idUser2);
        if (isFriend == 0){
            this.iFriendListService.addRequest(idUser1,idUser2);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }


    @DeleteMapping("/users/friendList/removeRequest/{idUser1}/{idUser2}")
    public ResponseEntity<Void>removeRequest(@PathVariable int idUser1, @PathVariable int idUser2){
        int isFriend = this.iFriendListService.checkFriend(idUser1,idUser2);
        if (isFriend == 51){
            this.iFriendListService.removeRequest(idUser1,idUser2);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
