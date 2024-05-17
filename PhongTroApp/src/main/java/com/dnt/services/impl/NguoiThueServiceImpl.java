/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.services.impl;

import com.dnt.pojo.NguoiThue;
import com.dnt.repositories.NguoiThueRepository;
import com.dnt.services.NguoiThueService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DikamonTu
 */
@Service
public class NguoiThueServiceImpl implements NguoiThueService{
    @Autowired
    private NguoiThueRepository nguoiThueRepo;
    
    @Override
    public List<NguoiThue> getNguoiThue(Map<String, String> params) {
        return this.nguoiThueRepo.getNguoiThue(params);
    }

    @Override
    public void addOrUpdateNguoiThue(NguoiThue n) {
            this.nguoiThueRepo.addOrUpdateNguoiThue(n);
    }

    @Override
    public NguoiThue getNguoiThueById(int id) {
        return this.nguoiThueRepo.getNguoiThueById(id);
    }

    @Override
    public void deleteNguoiThue(int id) {
        this.nguoiThueRepo.deleteNguoiThue(id);
    }
    
}
