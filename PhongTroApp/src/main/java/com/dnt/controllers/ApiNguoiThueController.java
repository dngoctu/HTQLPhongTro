/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.controllers;

import com.dnt.pojo.NguoiThue;
import com.dnt.services.NguoiThueService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @GetMapping("/nguoithue/")
    public ResponseEntity<List<NguoiThue>> list(@RequestParam  Map params) {
        return new ResponseEntity<>(this.nguoiThueService.getNguoiThue(params),
                HttpStatus.OK);
    }
    
    @GetMapping(path = "/nguoithue/{nguoithueId}/", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<NguoiThue> retrieve(@PathVariable(value = "nguoithueId") int id) {
        return new ResponseEntity<>(this.nguoiThueService.getNguoiThueById(id), HttpStatus.OK);
    }
    
    @DeleteMapping("/nguoithue/{nguoithueId}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable(value = "nguoithueId") int id) {
        this.nguoiThueService.deleteNguoiThue(id);
    }
} 

