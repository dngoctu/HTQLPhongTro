/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.controllers;

import com.dnt.pojo.KinhdoVido;
import com.dnt.services.KinhdoVidoService;
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
public class ApiKinhdoVidoController {
    @Autowired
    private KinhdoVidoService kinhdoVidoService;
    
    @GetMapping("/kinhdovido/")
    @CrossOrigin
    public ResponseEntity<List<KinhdoVido>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.kinhdoVidoService.getKinhdoVido(params), HttpStatus.OK);
    }
    
    @GetMapping(path = "/kinhdovido/{kinhdovidoId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<KinhdoVido> retrieve(@PathVariable(value = "kinhdovidoId") int id) {
        return new ResponseEntity<>(this.kinhdoVidoService.getKinhdoVidoById(id), HttpStatus.OK); 
    }
    
    @PostMapping(path = "/kinhdovido/")
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void create(@RequestBody @Valid KinhdoVido kv) {
        this.kinhdoVidoService.addOrUpdate(kv);
    }

    @PatchMapping(path = "/kinhdovido/{kinhdovidoId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    public void update(@PathVariable(value = "kinhdovidoId") int id, @RequestBody @Valid KinhdoVido kv) {
        kv.setId(id);
        this.kinhdoVidoService.addOrUpdate(kv);
    }

    @DeleteMapping("/kinhdovido/{kinhdovidoId}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin
    public void delete(Model model, @PathVariable(value = "kinhdovidoId") int id) {
        this.kinhdoVidoService.deleteKinhdoVido(id);
    }
}
