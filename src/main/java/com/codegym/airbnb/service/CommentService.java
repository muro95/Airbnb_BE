package com.codegym.airbnb.service;

import com.codegym.airbnb.message.response.CommentList;
import com.codegym.airbnb.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    List<CommentList> findAllByHouseId(Long houseId);

    void createComment(Comment comment);
}
