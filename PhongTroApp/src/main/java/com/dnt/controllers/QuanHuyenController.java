/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.controllers;

import com.dnt.pojo.QuanHuyen;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author DikamonTu
 */
@Controller
public class QuanHuyenController {
    @GetMapping("/quanhuyen")
    public String phongTroView(Model model) {
        model.addAttribute("quanhuyen", new QuanHuyen());
        return "quanhuyen";
    }
}
