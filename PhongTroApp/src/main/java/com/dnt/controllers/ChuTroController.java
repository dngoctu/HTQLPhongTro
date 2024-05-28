/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.controllers;

import com.dnt.pojo.ChuTro;
import com.dnt.services.ChuTroService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String chuTroView(Model model,
            @RequestParam Map<String, String> params) {
       int pageSize = Integer.parseInt(env.getProperty("nguoithue.pageSize").toString());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("chutro", this.chuTroService.getChuTro(params));
        return "chutro";
    }
    
    @GetMapping("/chutro/chutrodetails/{chutroId}")
    public String updateChuTroView(Model model, @PathVariable(value = "chutroId") int id) {
        model.addAttribute("chutro", this.chuTroService.getChuTroById(id));
        return "chutrodetails";
    }
    
    @GetMapping("/chutro/chutrodetails")
    public String createView(Model model) {
        model.addAttribute("chutro", new ChuTro());
        return "chutrodetails";
    }

    @PostMapping("/chutro/chutrodetails")
    public String addChuTroProcess(@ModelAttribute(value = "chutro") @Valid ChuTro n,
            BindingResult rs) {

        if (!rs.hasErrors()) {
            try {
                this.chuTroService.addOrUpdateChuTro(n);
                return "redirect:/chutro";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }

        return "chutrodetails";
    }
}
