/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.controllers;

import com.dnt.pojo.Follow;
import com.dnt.services.FollowService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DikamonTu
 */
@RestController
@RequestMapping("/api")
public class ApiFollowController {
    @Autowired
    private FollowService followService;
    
    @GetMapping("/follow/")
    @CrossOrigin
    public ResponseEntity<List<Follow>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.followService.getFollow(params), HttpStatus.OK);
    }
    
    @GetMapping(path = "/follow/{followId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Follow> retrieve(@PathVariable(value = "followId") int id) {
        return new ResponseEntity<>(this.followService.getFollowById(id), HttpStatus.OK); 
    }
    
    @PostMapping(path = "/follow/")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid Follow follow) {
        this.followService.addOrUpdate(follow);
    }

    @PatchMapping(path = "/follow/{followId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable(value = "followId") int followId, @RequestBody @Valid Follow follow) {
        follow.setId(followId);
        this.followService.addOrUpdate(follow);
    }

    @DeleteMapping("/follow/{followId}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Model model, @PathVariable(value = "followId") int id) {
        this.followService.deleteFollow(id);
    }
}
