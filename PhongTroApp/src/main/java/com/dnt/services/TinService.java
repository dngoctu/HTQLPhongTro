/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.services;

import com.dnt.pojo.Tin;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DikamonTu
 */
public interface TinService {
    List<Tin> getTin(Map<String, String> params);
    void addOrUpdate(Tin t);
    Tin getTinById(int id);
    void deleteTin(int id);    
}
