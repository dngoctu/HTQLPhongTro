/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.services;

import com.dnt.pojo.Follow;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DikamonTu
 */
public interface FollowService {
    List<Follow> getFollow(Map<String, String> params);
    void addOrUpdate(Follow f);
    Follow getFollowById(int id);
    void deleteFollow(int id);
}
