/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.controllers;

import com.dnt.services.ChuTroService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author DikamonTu
 */
@Controller
@PropertySource("classpath:configs.properties")
public class ChuTroController {
    @Autowired
    private ChuTroService chuTroService;
    @Autowired
    private Environment env;
    
    @RequestMapping("/chutro")
    public String nguoiThueView(Model model,
            @RequestParam Map<String, String> params) {
       int pageSize = Integer.parseInt(env.getProperty("nguoithue.pageSize").toString());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("chutro", this.chuTroService.getChuTro(params));
        return "chutro";
    }
}
