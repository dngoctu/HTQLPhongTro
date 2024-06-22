/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dnt.pojo.HinhAnhTro;
import com.dnt.pojo.PhongTro;
import com.dnt.services.ChuTroService;
import com.dnt.services.HinhAnhTroService;
import com.dnt.services.PhongTroService;
import com.dnt.services.QuanHuyenService;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@CrossOrigin
public class ApiPhongTroController {

    @Autowired
    private PhongTroService phongTroService;
    @Autowired
    private QuanHuyenService quanHuyenService;
    @Autowired
    private ChuTroService chuTroService;

    @GetMapping("/phongtro/")
    @CrossOrigin
    public ResponseEntity<List<PhongTro>> list(@RequestParam Map params) {
        return new ResponseEntity<>(this.phongTroService.getPhongTro(params),
                HttpStatus.OK);
    }

    @PostMapping(path = "/phongtro/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    }, produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PhongTro> create(@RequestBody @Valid PhongTro phongTro, HttpServletRequest request, HttpServletResponse response){
        this.phongTroService.addOrUpdate(phongTro);
        PhongTro created = this.phongTroService.getPhongTroById(phongTro.getId());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping(path = "/phongtro/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public void partialUpdate(@PathVariable("id") int id, @RequestBody @Valid PhongTro phongTro) {
        phongTro.setId(id);
        this.phongTroService.addOrUpdate(phongTro);
    }

    @GetMapping(path = "/phongtro/{phongtroId}/", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public ResponseEntity<PhongTro> retrieve(@PathVariable(value = "phongtroId") int id) {
        return new ResponseEntity<>(this.phongTroService.getPhongTroById(id), HttpStatus.OK);
    }

    @DeleteMapping("/phongtro/{phongtroId}/")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable(value = "phongtroId") int id) {
        this.phongTroService.deletePhongTro(id);
    }
}
