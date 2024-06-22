/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dnt.pojo.HinhAnhTro;
import com.dnt.repositories.HinhAnhTroRepository;
import com.dnt.services.HinhAnhTroService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DikamonTu
 */
@Service
public class HinhAnhTroServiceImpl implements HinhAnhTroService{
    @Autowired
    private HinhAnhTroRepository hinhAnhTroRepo;
    @Autowired
    private Cloudinary cloudinary;
    
    @Override
    public List<HinhAnhTro> getHinhAnhTro(Map<String, String> params) {
        return this.hinhAnhTroRepo.getHinhAnhTro(params);
    }

    @Override
    public void addOrUpdate(HinhAnhTro hinhanh) {
        if (!hinhanh.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(hinhanh.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                hinhanh.setHinhAnh(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(TaiKhoanServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.hinhAnhTroRepo.addOrUpdate(hinhanh);
    }

    @Override
    public HinhAnhTro getHinhAnhTroById(int id) {
        return this.hinhAnhTroRepo.getHinhAnhTroById(id);
    }

    @Override
    public void deleteHinhAnhTro(int id) {
        this.hinhAnhTroRepo.deleteHinhAnhTro(id);
    }
    
}
