/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.services.impl;

import com.dnt.pojo.ChuTro;
import com.dnt.repositories.ChuTroRepository;
import com.dnt.services.ChuTroService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DikamonTu
 */
@Service
public class ChuTroServiceImpl implements ChuTroService{
    @Autowired
    private ChuTroRepository chuTroRepo;

    @Override
    public List<ChuTro> getChuTro(Map<String, String> params) {
        return this.chuTroRepo.getChuTro(params);
    }

    @Override
    public void addOrUpdateChuTro(ChuTro c) {
        this.chuTroRepo.addOrUpdateChuTro(c);
    }

    @Override
    public ChuTro getChuTroById(int id) {
        return this.chuTroRepo.getChuTroById(id);
    }

    @Override
    public void deleteChuTro(int id) {
        this.chuTroRepo.deleteChuTro(id);
    }
    
}
