/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author DikamonTu
 */
@Controller
@PropertySource("classpath:configs.properties")
public class RegistrationController {
     @Autowired
    private Environment env;
    
    @RequestMapping("/register")
    public String showRegistrationForm(Model model) { 
        List<String> roles = new ArrayList<>();
        roles.add(env.getProperty("taiKhoan.admin"));
        roles.add(env.getProperty("taiKhoan.chuTro"));
        roles.add(env.getProperty("taiKhoan.nguoiThue"));
        model.addAttribute("vaitro", roles);
        
        return "register";
    }
    
}
