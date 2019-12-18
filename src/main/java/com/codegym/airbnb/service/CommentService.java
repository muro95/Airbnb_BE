package com.codegym.airbnb.service;

import com.codegym.airbnb.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    List<Comment> findAllByHouseId(Long id);

    void createComment(Comment comment);
}
