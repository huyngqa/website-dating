package com.codegym.dating.controller;

import com.codegym.dating.dto.UserDto;
import com.codegym.dating.service.IFriendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api")
public class FriendRequestRestfulController {
    @Autowired
    private IFriendListService friendListService;

    @GetMapping("users/request/{id}")
    public ResponseEntity<List<UserDto>> findAllRequest(@PathVariable int id) {
        List<UserDto> userDtoList = this.friendListService.findAllRequest(id);
        if (userDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);

    }

    @PatchMapping("users/accept/{id1}/{id2}")
    public ResponseEntity<Void> acceptRequest(@PathVariable int id1,
                                              @PathVariable int id2) {
        friendListService.acceptRequest(id1, id2);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("users/denied/{id1}/{id2}")
    public ResponseEntity<Void> deniedRequest(@PathVariable Integer id1,
                                              @PathVariable Integer id2) {
        friendListService.deniedRequest(id1, id2);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("users/suggest/{idUserSuggest}/{gender}")
    public ResponseEntity<List<UserDto>> suggestRequest(@PathVariable Integer idUserSuggest, @PathVariable boolean gender) {return new ResponseEntity<>(friendListService.suggestRequest(idUserSuggest, gender),HttpStatus.OK);
    }

}