/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.services.impl;

import com.dnt.pojo.ThanhPho;
import com.dnt.repositories.ThanhPhoRepository;
import com.dnt.services.ThanhPhoService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DikamonTu
 */
@Service
public class ThanhPhoServiceImpl implements ThanhPhoService{
    @Autowired
    private ThanhPhoRepository thanhPhoRepo;
    
    @Override
    public List<ThanhPho> getThanhPho(Map<String, String> params) {
        return this.thanhPhoRepo.getThanhPho(params);
    }

    @Override
    public void addOrUpdate(ThanhPho tp) {
        this.thanhPhoRepo.addOrUpdate(tp);
    }

    @Override
    public ThanhPho getThanhPhoById(int id) {
        return this.thanhPhoRepo.getThanhPhoById(id);
    }

    @Override
    public void deleteThanhPho(int id) {
        this.thanhPhoRepo.deleteThanhPho(id);
    }
    
}
