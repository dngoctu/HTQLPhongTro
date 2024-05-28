/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.repositories;

import com.dnt.pojo.TaiKhoan;
import java.util.List;

/**
 *
 * @author DikamonTu
 */
public interface TaiKhoanRepository {
    List<TaiKhoan> getTaiKhoan();
    TaiKhoan getTaiKhoanByUsername(String username);
    TaiKhoan getTaiKhoanById(int id);
    void addTaiKhoan(TaiKhoan taikhoan);
}
