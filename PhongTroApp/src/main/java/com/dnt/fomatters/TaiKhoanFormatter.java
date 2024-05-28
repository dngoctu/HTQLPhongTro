/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.fomatters;

import com.dnt.pojo.TaiKhoan;
import com.dnt.services.TaiKhoanService;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

/**
 *
 * @author DikamonTu
 */
public class TaiKhoanFormatter implements Formatter<TaiKhoan>{
    @Autowired
    private TaiKhoanService taiKhoanService;
    
    @Override
    public TaiKhoan parse(String username, Locale locale) throws ParseException {
            TaiKhoan tk = new TaiKhoan();
        //            int id = Integer.parseInt(idtaiKhoan);
            tk.setUsername(username);
            return tk; 
       
    }

    @Override
    public String print(TaiKhoan tk, Locale locale) {
        return String.valueOf(tk.getId());
    }
}
