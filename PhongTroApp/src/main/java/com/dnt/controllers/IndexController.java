/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.controllers;

import com.dnt.services.ChuTroService;
import com.dnt.services.HinhAnhTroService;
import com.dnt.services.PhongTroService;
import com.dnt.services.QuanHuyenService;
import com.dnt.services.TaiKhoanService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author admin
 */
@Controller
@ControllerAdvice
public class IndexController {
    @Autowired
    private PhongTroService phongTroService;
    @Autowired
    private Environment env;
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private QuanHuyenService quanHuyenService;
    @Autowired
    private ChuTroService chuTroService;
    @Autowired
    private HinhAnhTroService hinhAnhTroService;
    
    @ModelAttribute
    public void commonAttr(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("taiKhoan", this.taiKhoanService.getTaiKhoan());
        model.addAttribute("quanHuyen", this.quanHuyenService.getQuanHuyen(params));
        model.addAttribute("chuTro", this.chuTroService.getChuTro(params));
    }
    
    @RequestMapping("/")
    public String nguoiThueView(Model model,
            @RequestParam Map<String, String> params) {
        int pageSize = Integer.parseInt(env.getProperty("nguoithue.pageSize"));
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("phongtro", this.phongTroService.getPhongTro(params));
        model.addAttribute("hinhanhtro", this.hinhAnhTroService.getHinhAnhTro(params));

        return "index";
    }
}
