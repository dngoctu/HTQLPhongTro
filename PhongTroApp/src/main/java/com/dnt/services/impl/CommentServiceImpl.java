/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.services.impl;

import com.dnt.pojo.Comment;
import com.dnt.repositories.CommentRepository;
import com.dnt.services.CommentService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DikamonTu
 */
@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepo;

    @Override
    public List<Comment> getComments(int id){
        return this.commentRepo.getComments(id);
    }

    @Override
    public void addOrUpdate(Comment c) {
        this.commentRepo.addOrUpdate(c);
    }

    @Override
    public Comment getCommentById(int id) {
        return this.commentRepo.getCommentById(id);
    }

    @Override
    public void deleteComment(int id) {
        this.commentRepo.deleteComment(id);
    }
}
