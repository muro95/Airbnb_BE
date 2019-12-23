package com.codegym.airbnb.service.impl;

import com.codegym.airbnb.message.response.CommentList;
import com.codegym.airbnb.model.Comment;
import com.codegym.airbnb.repository.CommentDao;
import com.codegym.airbnb.repository.CommentRepository;
import com.codegym.airbnb.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentDao commentDao;

    @Override
    public List<CommentList> findAllByHouseId(Long houseId) {
        return commentDao.getListComment(houseId) ;
    }

    @Override
    public void createComment(Comment comment) {
        this.commentRepository.save(comment);
    }
}
