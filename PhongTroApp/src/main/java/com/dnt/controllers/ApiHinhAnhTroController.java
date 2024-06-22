/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dnt.pojo.HinhAnhTro;
import com.dnt.services.HinhAnhTroService;
import com.dnt.services.PhongTroService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author DikamonTu
 */
@RestController
@RequestMapping("/api")
public class ApiHinhAnhTroController {
    @Autowired
    private HinhAnhTroService hinhAnhTroService;
    @Autowired
    private PhongTroService phongTroService;
    @Autowired
    private Cloudinary cloudinary;
    
    @GetMapping("/hinhanhtro/")
    @CrossOrigin
    public ResponseEntity<List<HinhAnhTro>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.hinhAnhTroService.getHinhAnhTro(params), HttpStatus.OK);
    }
    
    @PostMapping(path = "/hinhanhtro/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    }, produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam Map<String, String> params, @RequestPart MultipartFile[] files
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
    }
    
    @DeleteMapping("/hinhanhtro/{hinhanhtroId}/")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable(value = "hinhanhtroId") int id) {
        this.hinhAnhTroService.deleteHinhAnhTro(id);
    }
}
