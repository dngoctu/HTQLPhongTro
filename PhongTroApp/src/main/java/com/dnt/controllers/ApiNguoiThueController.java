/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.controllers;

import com.dnt.pojo.NguoiThue;
import com.dnt.pojo.TaiKhoan;
import com.dnt.services.NguoiThueService;
import com.dnt.services.TaiKhoanService;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DikamonTu
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiNguoiThueController {
    @Autowired
    private NguoiThueService nguoiThueService;
    @Autowired
    private TaiKhoanService taiKhoanService;
    
    @GetMapping("/nguoithue/")
    @CrossOrigin
    public ResponseEntity<List<NguoiThue>> list(@RequestParam  Map params) {
        return new ResponseEntity<>(this.nguoiThueService.getNguoiThue(params),
                HttpStatus.OK);
    }
    
    @PostMapping(path = "/nguoithue/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    }, produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public ResponseEntity<NguoiThue> create(@RequestParam Map<String, String> params,
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        NguoiThue c = new NguoiThue();
        c.setTen(params.get("ten"));
        c.setHo(params.get("ho"));
        c.setSdt(params.get("sdt"));
        c.setDiaChi(params.get("diaChi"));
        c.setNgayTao(new Date());
        c.setIdtaiKhoan(taiKhoanService.getTaiKhoanById(Integer.parseInt(params.get("idtaiKhoan"))));
        this.nguoiThueService.addOrUpdateNguoiThue(c);
        NguoiThue createdNguoiThue = this.nguoiThueService.getNguoiThueById(c.getId());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNguoiThue);
    }
    
    @GetMapping(path = "/nguoithue/{nguoithueId}/", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public ResponseEntity<NguoiThue> retrieve(@PathVariable(value = "nguoithueId") int id) {
        return new ResponseEntity<>(this.nguoiThueService.getNguoiThueById(id), HttpStatus.OK);
    }
        
    @DeleteMapping("/nguoithue/{nguoithueId}/")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable(value = "nguoithueId") int id) {
        this.nguoiThueService.deleteNguoiThue(id);
    }
} 

