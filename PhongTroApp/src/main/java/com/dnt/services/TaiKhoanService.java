/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.services;

import com.dnt.pojo.TaiKhoan;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author DikamonTu
 */
public interface TaiKhoanService extends UserDetailsService{
    List<TaiKhoan> getTaiKhoan();
    TaiKhoan getTaiKhoanByUsername(String username);
    TaiKhoan getTaiKhoanById(int id);
    void addTaiKhoan(TaiKhoan taikhoan);
}
