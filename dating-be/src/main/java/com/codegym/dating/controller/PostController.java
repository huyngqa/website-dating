package com.codegym.dating.controller;

import com.codegym.dating.dto.IPostDto;
import com.codegym.dating.dto.PostDto;
import com.codegym.dating.model.Post;
import com.codegym.dating.model.User;
import com.codegym.dating.service.IPostService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api")
public class PostController {
    @Autowired
    IPostService iPostService;

    @GetMapping("/users/list/{id}")
    public ResponseEntity<List<IPostDto>> getList(@PathVariable int id) {
        List<IPostDto> list = this.iPostService.getPostList(id);
        if(list.isEmpty()){
            return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
    }

    @GetMapping("/users/personList/{id}")
    public ResponseEntity<List<IPostDto>> getPersonList(@PathVariable int id){
        List<IPostDto> list = this.iPostService.getUserPostList(id);
        if (list.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
    }

    @GetMapping("/users/findPost/{id}")
    public  ResponseEntity<IPostDto> findByIdPost(@PathVariable int id){
        IPostDto post = this.iPostService.findPostById(id);

        if (post == null ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(post,HttpStatus.OK);
        }
    }

    @PatchMapping("/users/update/{id}")
    public ResponseEntity<Void> updatePost( @RequestBody @Valid PostDto postDto,
                                            BindingResult bindingResult,
                                            @PathVariable int id){
        Post post = new Post();
        User user = new User();
        user.setIdUser(id);
        new PostDto().validate(postDto,bindingResult);
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            BeanUtils.copyProperties(postDto,post);
            post.setUser(user);
            this.iPostService.updatePost(post);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
