/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dnt.pojo.TaiKhoan;
import com.dnt.repositories.TaiKhoanRepository;
import com.dnt.services.TaiKhoanService;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DikamonTu
 */
@Service("userDetailsService")
@Transactional
public class TaiKhoanServiceImpl implements TaiKhoanService{
    @Autowired
    private TaiKhoanRepository taiKhoanRepo;
    @Autowired
    private BCryptPasswordEncoder passEncoder;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<TaiKhoan> getTaiKhoan() {
        return this.taiKhoanRepo.getTaiKhoan();
    }
   
    
    @Override
    public TaiKhoan getTaiKhoanByUsername(String username) {
        return this.taiKhoanRepo.getTaiKhoanByUsername(username);
    }

    @Override
    public TaiKhoan getTaiKhoanById(int id) {
        return this.taiKhoanRepo.getTaiKhoanById(id);
    }  
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TaiKhoan tk = this.taiKhoanRepo.getTaiKhoanByUsername(username);
        if (tk == null) {
            throw new UsernameNotFoundException("Không tồn tại!");
        }
        
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(tk.getVaiTro()));
        
        return new org.springframework.security.core.userdetails.User(
                tk.getUsername(), tk.getPassword(), authorities);
    }
    
    @Override
    public void addTaiKhoan(TaiKhoan taikhoan) {
        if (!taikhoan.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(taikhoan.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                taikhoan.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(TaiKhoanServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.taiKhoanRepo.addTaiKhoan(taikhoan);
    }

    @Override
    public boolean authTaiKhoan(String username, String password) {
        return this.taiKhoanRepo.authTaiKhoan(username, password);
    }
}
