/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.controllers;

import com.dnt.pojo.ChuTro;
import com.dnt.services.ChuTroService;
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
public class ApiChuTroController {
    @Autowired
    private ChuTroService chuTroService;
    
    @GetMapping("/chutro/")
    public ResponseEntity<List<ChuTro>> list(@RequestParam  Map params) {
        return new ResponseEntity<>(this.chuTroService.getChuTro(params),
                HttpStatus.OK);
    }
    
    @GetMapping(path = "/chutro/{chutroId}/", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<ChuTro> retrieve(@PathVariable(value = "chutroId") int id) {
        return new ResponseEntity<>(this.chuTroService.getChuTroById(id), HttpStatus.OK);
    }
    
    @DeleteMapping("/chutro/{chutroId}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable(value = "chutroId") int id) {
        this.chuTroService.deleteChuTro(id);
    }
}   
