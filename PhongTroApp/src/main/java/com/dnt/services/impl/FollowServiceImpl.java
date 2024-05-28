/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.services.impl;

import com.dnt.pojo.Follow;
import com.dnt.repositories.FollowRepository;
import com.dnt.services.FollowService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DikamonTu
 */
@Service
public class FollowServiceImpl implements FollowService{
    @Autowired
    private FollowRepository followRepo;

    @Override
    public List<Follow> getFollow(Map<String, String> params) {
        return this.followRepo.getFollow(params);
    }

    @Override
    public void addOrUpdate(Follow f) {
        this.followRepo.addOrUpdate(f);
    }

    @Override
    public Follow getFollowById(int id) {
        return this.followRepo.getFollowById(id);
    }

    @Override
    public void deleteFollow(int id) {
        this.followRepo.deleteFollow(id);
    }
}
