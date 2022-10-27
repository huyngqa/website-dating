package com.codegym.dating.service.impl;

import com.codegym.dating.dto.ICommentDto;
import com.codegym.dating.model.Comment;
import com.codegym.dating.repository.ICommentRepository;
import com.codegym.dating.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private ICommentRepository iCommentRepository;

    @Override
    public void addComment(Comment comments) {

        this.iCommentRepository.addComment(comments.getContent(),
                comments.getPost().getIdPost(), comments.getUser().getIdUser());
    }

    @Override
    public List<ICommentDto> displayComment(Integer id_post) {
        return this.iCommentRepository.displayComment(id_post);
    }


}
