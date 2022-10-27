package com.codegym.dating.controller;

import com.codegym.dating.dto.CommentDto;
import com.codegym.dating.dto.ICommentDto;
import com.codegym.dating.model.Comment;
import com.codegym.dating.service.ICommentService;
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
public class CommentRestController {

    @Autowired
    private ICommentService icommentService;


    @PostMapping("users/add_comment")
    public ResponseEntity<Comment> getAddComment(@RequestBody @Valid CommentDto commentDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }

        Comment comment = new Comment();

        BeanUtils.copyProperties(commentDto, comment);

        this.icommentService.addComment(comment);

        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("users/display_comment/{id}")
    public ResponseEntity<List<ICommentDto>> displayComment(@PathVariable Integer id) {

        List<ICommentDto> comments = this.icommentService.displayComment(id);

        if (comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(comments, HttpStatus.OK);

    }

}
