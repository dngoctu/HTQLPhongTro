/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.services.impl;

import com.dnt.pojo.KinhdoVido;
import com.dnt.repositories.KinhdoVidoRepository;
import com.dnt.services.KinhdoVidoService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DikamonTu
 */
@Service
public class KinhdoVidoServiceImpl implements KinhdoVidoService{
    @Autowired
    private KinhdoVidoRepository kinhdoVidoRepo;
    
    @Override
    public List<KinhdoVido> getKinhdoVido(Map<String, String> params) {
        return this.kinhdoVidoRepo.getKinhdoVido(params);
    }

    @Override
    public void addOrUpdate(KinhdoVido kv) {
        this.kinhdoVidoRepo.addOrUpdate(kv);
    }

    @Override
    public KinhdoVido getKinhdoVidoById(int id) {
        return this.kinhdoVidoRepo.getKinhdoVidoById(id);
    }

    @Override
    public void deleteKinhdoVido(int id) {
        this.kinhdoVidoRepo.deleteKinhdoVido(id);
    }
    
}
