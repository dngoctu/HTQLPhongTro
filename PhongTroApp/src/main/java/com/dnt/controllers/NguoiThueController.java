/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.controllers;

import com.dnt.services.NguoiThueService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@PropertySource("classpath:configs.properties")
public class NguoiThueController {
    @Autowired
    private NguoiThueService nguoiThueService;
    @Autowired
    private Environment env;
    
    @RequestMapping("/nguoithue")
    public String nguoiThueView(Model model,
            @RequestParam Map<String, String> params) {
        int pageSize = Integer.parseInt(env.getProperty("nguoithue.pageSize").toString());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("nguoithue", this.nguoiThueService.getNguoiThue(params));
        return "nguoithue";
    }
}
