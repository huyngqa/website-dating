package com.codegym.dating.controller;

import com.codegym.dating.dto.IPostDto;
import com.codegym.dating.dto.PostDto;
import com.codegym.dating.model.Post;
import com.codegym.dating.model.User;
import com.codegym.dating.service.IPostService;
import com.codegym.dating.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("api")
public class PostRestController {
    @Autowired
    private IPostService iPostService;

    @Autowired
    private IUserService iUserService;

    @PostMapping("/users/posts/save")
    public ResponseEntity<Void> savePost(@RequestBody @Valid PostDto postDto, BindingResult bindingResult){
        new PostDto().validate(postDto, bindingResult);

        User user = iUserService.findUserById(postDto.getUser().getIdUser());

        if (user==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Post post = new Post();
        BeanUtils.copyProperties(postDto,post);
        iPostService.save(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users/posts/personList/{id}")
    public ResponseEntity<List<IPostDto>> getPersonList(@PathVariable int id){
        List<IPostDto> list = this.iPostService.getUserPostList(id);
        if (list.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
    }
}
