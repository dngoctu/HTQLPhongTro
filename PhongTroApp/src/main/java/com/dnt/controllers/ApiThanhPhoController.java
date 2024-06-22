/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.controllers;

import com.dnt.pojo.ThanhPho;
import com.dnt.services.ThanhPhoService;
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
public class ApiThanhPhoController {
    @Autowired
    private ThanhPhoService thanhPhoService;
    
    @GetMapping("/thanhpho/")
    @CrossOrigin
    public ResponseEntity<List<ThanhPho>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.thanhPhoService.getThanhPho(params), HttpStatus.OK);
    }
    
    @GetMapping(path = "/thanhpho/{thanhphoId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<ThanhPho> retrieve(@PathVariable(value = "thanhphoId") int id) {
        return new ResponseEntity<>(this.thanhPhoService.getThanhPhoById(id), HttpStatus.OK); 
    }
    
    @PostMapping(path = "/thanhpho/")
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void create(@RequestBody @Valid ThanhPho thanhpho) {
        this.thanhPhoService.addOrUpdate(thanhpho);
    }

    @PatchMapping(path = "/thanhpho/{thanhphoId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    public void update(@PathVariable(value = "thanhphoId") int id, @RequestBody @Valid ThanhPho thanhpho) {
        thanhpho.setId(id);
        this.thanhPhoService.addOrUpdate(thanhpho);
    }

    @DeleteMapping("/thanhpho/{thanhphoId}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin
    public void delete(Model model, @PathVariable(value = "thanhphoId") int id) {
        this.thanhPhoService.deleteThanhPho(id);
    }
}
