/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.controllers;

import com.dnt.pojo.Comment;
import com.dnt.services.CommentService;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author DikamonTu
 */
@RestController
@RequestMapping("/api")
public class ApiCommentController {
    @Autowired
    private CommentService commentService;
    
    @GetMapping(path = "/comment/{commentId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Comment> retrieve(@PathVariable(value = "commentId") int id) {
        return new ResponseEntity<>(this.commentService.getCommentById(id), HttpStatus.OK); 
    }
    
    @PostMapping(path = "/comment/")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid Comment comment) {
        this.commentService.addOrUpdate(comment);
    }

    @PatchMapping(path = "/comment/{commentId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable(value = "commentId") int commentId, @RequestBody Map<String, Object> updates) {
        Comment currentComment = this.commentService.getCommentById(commentId);

        if (currentComment == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found");
        }

        // Update fields selectively
        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Comment.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, currentComment, value);
            }
        });

        this.commentService.addOrUpdate(currentComment);
    }

    @DeleteMapping("/comment/{commentId}/")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Model model, @PathVariable(value = "commentId") int id) {
        this.commentService.deleteComment(id);
    }
}
