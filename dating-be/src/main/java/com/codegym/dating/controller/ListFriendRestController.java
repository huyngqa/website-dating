package com.codegym.dating.controller;

import com.codegym.dating.projection_dto.IUserDto;
import com.codegym.dating.service.IFriendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api")
public class ListFriendRestController {
    @Autowired
    private IFriendListService iFriendListService;

    @GetMapping("/users/list/friend_list/{id}")
    public ResponseEntity<Page<IUserDto>> showFriendList(@PageableDefault(size = 4) Pageable pageable,
                                                         @PathVariable Integer id,
                                                         @RequestParam Optional<String> name) {
        String keyWord = name.orElse("");
        Page<IUserDto> friendLists = iFriendListService.findAllFriendList(id, keyWord, pageable);
        if (!friendLists.hasContent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(friendLists, HttpStatus.OK);
    }

    @PatchMapping("/users/list/delete/{idUser}")
    public ResponseEntity<Void>delete(@PathVariable Integer idUser, @RequestBody Integer[] listFriend){
        this.iFriendListService.deleteFriend(idUser,listFriend);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/users/list/block/{idUser}")
    public ResponseEntity<Void> block(@PathVariable Integer idUser,@RequestBody Integer[] listFriend){
        this.iFriendListService.blockFriend(idUser,listFriend);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
