/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.services.impl;

import com.dnt.pojo.QuanHuyen;
import com.dnt.repositories.QuanHuyenRepository;
import com.dnt.services.QuanHuyenService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DikamonTu
 */
@Service
public class QuanHuyenServiceImpl implements QuanHuyenService{
    @Autowired
    private QuanHuyenRepository quanHuyenRepo;
    
    @Override
    public List<QuanHuyen> getQuanHuyen(Map<String, String> params) {
        return this.quanHuyenRepo.getQuanHuyen(params);
    }

    @Override
    public void addOrUpdate(QuanHuyen q) {
        this.quanHuyenRepo.addOrUpdate(q);
    }

    @Override
    public QuanHuyen getQuanHuyenById(int id) {
        return this.quanHuyenRepo.getQuanHuyenById(id);
    }

    @Override
    public void deleteQuanHuyen(int id) {
        this.quanHuyenRepo.deleteQuanHuyen(id);
    }
    
}
