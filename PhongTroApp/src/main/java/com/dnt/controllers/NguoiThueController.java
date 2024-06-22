/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.controllers;

import com.dnt.pojo.NguoiThue;
import com.dnt.services.NguoiThueService;
import com.dnt.services.TaiKhoanService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@ControllerAdvice
@PropertySource("classpath:configs.properties")
public class NguoiThueController {

    @Autowired
    private NguoiThueService nguoiThueService;
     
    @Autowired
    private TaiKhoanService taiKhoanService;
    
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

    @GetMapping("/nguoithue/nguoithuedetails/{nguoithueId}")
    public String updateNguoiThueView(Model model, @PathVariable(value = "nguoithueId") int id) {
        model.addAttribute("nguoithue", this.nguoiThueService.getNguoiThueById(id));
        return "nguoithuedetails";
    }
    
    @GetMapping("/nguoithue/nguoithuedetails")
    public String createView(Model model) {
        model.addAttribute("nguoithue", new NguoiThue());
        return "nguoithuedetails";
    }

    @PostMapping("/nguoithue/nguoithuedetails")
    public String addNguoiThueProcess(@ModelAttribute(value = "nguoithue") @Valid NguoiThue n,
            BindingResult rs) {

        if (!rs.hasErrors()) {
            try {
                this.nguoiThueService.addOrUpdateNguoiThue(n);
                return "redirect:/nguoithue";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }

        return "nguoithuedetails";
    }
}
