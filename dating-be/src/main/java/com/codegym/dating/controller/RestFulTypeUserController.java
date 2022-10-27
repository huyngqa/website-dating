package com.codegym.dating.controller;

import com.codegym.dating.model.TypeUser;
import com.codegym.dating.service.ITypeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "api/admin")
public class RestFulTypeUserController {

    @Autowired
    private ITypeUserService iTypeUserService;

    @GetMapping(value = "/list/typeUser")
    public ResponseEntity<?> getAllTypeUser(){
        List<TypeUser> typeUsers = iTypeUserService.findAllTypeUser();
        return new ResponseEntity<>(typeUsers, HttpStatus.OK);
    }
}
