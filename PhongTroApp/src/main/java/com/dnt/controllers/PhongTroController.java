/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dnt.pojo.HinhAnhTro;
import com.dnt.pojo.PhongTro;
import com.dnt.services.HinhAnhTroService;
import com.dnt.services.PhongTroService;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author DikamonTu
 */
@Controller
public class PhongTroController {
    @Autowired
    private HinhAnhTroService hinhAnhTroService;
    @Autowired
    private PhongTroService phongTroService;
    @Autowired
    private Cloudinary cloudinary;
    
    @GetMapping("/phongtro")
    public String phongTroView(Model model) {
        model.addAttribute("phongtro", new PhongTro());
        return "phongtro";
    }
    
    @GetMapping("/hinhanhtro")
    public String hinhAnhTroView(Model model, @RequestParam  Map params) {
        model.addAttribute("hinhAnhTro", new HinhAnhTro());
        model.addAttribute("phongtro", this.phongTroService.getPhongTro(params));
        return "hinhanhtro";
    }
    
    @PostMapping(path = "/hinhanhtro", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    }, produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public String create(@RequestParam Map<String, String> params, @RequestPart MultipartFile[] files
            , HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idphongTro = Integer.parseInt(params.get("idphongTro"));
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue; 
            }
            try {
                HinhAnhTro hinhAnhTro = new HinhAnhTro();
                hinhAnhTro.setIdphongTro(phongTroService.getPhongTroById(idphongTro));
                hinhAnhTro.setFile(file);
                Map res = this.cloudinary.uploader().upload(hinhAnhTro.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                hinhAnhTro.setHinhAnh(res.get("secure_url").toString());

                this.hinhAnhTroService.addOrUpdate(hinhAnhTro);
            } catch (IOException e) {
            } 
    }
        return "redirect:/";
    }
    
    @GetMapping("/phongtro/{phongtroId}")
    public String getDetailsPhongTroView(Model model, @PathVariable(value = "phongtroId") int id) {
        model.addAttribute("phongtro", this.phongTroService.getPhongTroById(id));
        
        return "phongtro";
    }
    
    @PostMapping("/phongtro")
    public String addPhongTroProcess(Model model, @ModelAttribute(value = "phongtro") @Valid PhongTro p,
            BindingResult rs) {
       
        if (!rs.hasErrors()) {
            try {
                this.phongTroService.addOrUpdate(p);
                
                return "redirect:/";
            } catch (Exception ex) {
                model.addAttribute("errMsg", ex.toString());
            }
        }
        
        return "phongtro";
    }
}
