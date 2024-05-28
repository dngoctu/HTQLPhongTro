/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.services.impl;

import com.dnt.pojo.Tin;
import com.dnt.repositories.TinRepository;
import com.dnt.services.TinService;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DikamonTu
 */
@Service
public class TinServiceImpl implements TinService{
    @Autowired
    private TinRepository tinRepo;
    
    @Override
    public List<Tin> getTin(Map<String, String> params) {
        return this.tinRepo.getTin(params);
    }

    @Override
    @Transactional
    public void addOrUpdate(Tin t) {
        this.tinRepo.addOrUpdate(t);
    }

    @Override
    public Tin getTinById(int id) {
        return this.tinRepo.getTinById(id);
    }

    @Override
    public void deleteTin(int id) {
        this.tinRepo.deleteTin(id);
    }
    
}
