package com.codegym.airbnb.service.impl;

import com.codegym.airbnb.model.Comment;
import com.codegym.airbnb.repository.CommentRepository;
import com.codegym.airbnb.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> findAllByHouseId(Long houseId) {
        return this.commentRepository.findAllByHouseId(houseId);
    }

    @Override
    public void createComment(Comment comment) {
        this.commentRepository.save(comment);
    }
}
