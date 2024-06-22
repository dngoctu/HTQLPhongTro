/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.controllers;

import com.dnt.pojo.QuanHuyen;
import com.dnt.services.QuanHuyenService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class ApiQuanHuyenController {
    @Autowired
    private QuanHuyenService quanHuyenService;
    
    @GetMapping("/quanhuyen/")
    @CrossOrigin
    public ResponseEntity<List<QuanHuyen>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.quanHuyenService.getQuanHuyen(params), HttpStatus.OK);
    }
    
    @GetMapping(path = "/quanhuyen/{quanhuyenId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<QuanHuyen> retrieve(@PathVariable(value = "quanhuyenId") int id) {
        return new ResponseEntity<>(this.quanHuyenService.getQuanHuyenById(id), HttpStatus.OK); 
    }
    
    @PostMapping(path = "/quanhuyen/")
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void create(@RequestBody @Valid QuanHuyen qh) {
        this.quanHuyenService.addOrUpdate(qh);
    }

    @PatchMapping(path = "/quanhuyen/{quanhuyenId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    public void update(@PathVariable(value = "quanhuyenId") int id, @RequestBody @Valid QuanHuyen qh) {
        qh.setId(id);
        this.quanHuyenService.addOrUpdate(qh);
    }

    @DeleteMapping("/quanhuyen/{quanhuyenId}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin
    public void delete(Model model, @PathVariable(value = "quanhuyenId") int id) {
        this.quanHuyenService.deleteQuanHuyen(id);
    }
}
