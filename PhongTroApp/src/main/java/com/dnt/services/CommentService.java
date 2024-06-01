/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.services;

import com.dnt.pojo.Comment;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DikamonTu
 */
public interface CommentService {
    List<Comment> getComments(int id);
    void addOrUpdate(Comment c);
    Comment getCommentById(int id);
    void deleteComment(int id);
}
