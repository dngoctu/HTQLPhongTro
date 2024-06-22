/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.services.impl;

import com.dnt.pojo.PhongTro;
import com.dnt.repositories.PhongTroRepository;
import com.dnt.services.PhongTroService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DikamonTu
 */
@Service
public class PhongTroServiceImpl implements PhongTroService{
    @Autowired
    private PhongTroRepository phongTroRepo;
    
    @Override
    public List<PhongTro> getPhongTro(Map<String, String> params) {
        return this.phongTroRepo.getPhongTro(params);
    }

    @Override
    public void addOrUpdate(PhongTro p) {
        this.phongTroRepo.addOrUpdate(p);
    }

    @Override
    public PhongTro getPhongTroById(int id) {
        return this.phongTroRepo.getPhongTroById(id);
    }

    @Override
    public void deletePhongTro(int id) {
        this.phongTroRepo.deletePhongTro(id);
    }
    
}
